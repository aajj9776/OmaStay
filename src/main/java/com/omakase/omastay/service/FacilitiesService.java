package com.omakase.omastay.service;
import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.HostAvgPriceDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.QImage;
import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.mapper.FacilitiesMapper;
import com.omakase.omastay.repository.*;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.Tuple;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import static com.omakase.omastay.entity.QHostInfo.hostInfo;
import static com.omakase.omastay.entity.QImage.image;
import static com.omakase.omastay.entity.QReview.review;
import static com.omakase.omastay.entity.QRoomInfo.roomInfo;

@Service
public class FacilitiesService {
    @Autowired
    private HostInfoRepository hostInfoRepository;

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    PriceRepository priceRepository;

    public List<FacilitiesDTO> all() {

        List<Facilities> facilities = facilitiesRepository.findAll();

        return FacilitiesMapper.INSTANCE.toFacilitiesDTOList(facilities);
    }

    //숙소 검색 필터링
    // 숙소 검색 필터링
    public List<ResultAccommodationsDTO> search(FilterDTO filterDTO) {
        // 검색어 필터링 된 룸인포 키값과 호스트인포 키값 리스트 가져오기(예약이 불가능한 객실 포함)
        List<Tuple> allHostRoomIds = searchKeyword(filterDTO);

        // 튜플에서 룸인포 키값만 가져오기
        List<Integer> roomIdxs = allHostRoomIds.stream()
                .map(tuple -> tuple.get(roomInfo.id))
                .collect(Collectors.toList());

        System.out.println("룸인포 키값만 가져오기=" + roomIdxs);

        // 검색어의 해당하는 리스트 중 해당 날짜 예약이 가능한 룸인포 키값과 호스트인포 키값 필터링(roomInfo.id 리스트)
        List<Tuple> availableHost = roomInfoRepository.dateFiltering(filterDTO.getStartEndDay(), roomIdxs);

        // 호스트 키값이랑 호텔 이름, x좌표, y좌표 가져오기(ResultAccommodationsDTO에 셋)
        List<Tuple> hostInfos = roomInfoRepository.findHostsByRoomIds(roomIdxs);

        System.out.println("호스트 키값이랑 호텔 이름, x좌표, y좌표 가져오기=" + hostInfos);

        // 튜플에서 호스트 키값만 가져오기
        List<Integer> hostIds = hostInfos.stream()
                .map(tuple -> tuple.get(hostInfo.id))
                .collect(Collectors.toList());

        // 예약 가능한 룸 ID 추출
        Set<Integer> availableRoomIds = availableHost.stream()
                .map(tuple -> tuple.get(roomInfo.id))
                .collect(Collectors.toSet());

        // 예약 가능한 호스트 ID 추출
        List<Integer> availableHostIds = availableHost.stream()
                .map(tuple -> tuple.get(hostInfo.id)).distinct().collect(Collectors.toList());

        System.out.println("예약 가능한 룸 ID=" + availableRoomIds);

        // 호스트별로 룸을 매핑
        Map<Integer, List<Tuple>> hostToRoomsMap = allHostRoomIds.stream()
                .collect(Collectors.groupingBy(tuple -> tuple.get(hostInfo.id)));

        // 예약 불가능한 호스트 ID 추출
        Set<Integer> unavailableHostIds = hostToRoomsMap.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .map(tuple -> tuple.get(roomInfo.id))
                        .allMatch(roomId -> !availableRoomIds.contains(roomId)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        System.out.println("예약 불가능한 호스트 ID=" + unavailableHostIds);

        // 리뷰 평점과 리뷰 몇 명이 남겼는지 가져오기(ResultAccommodationsDTO에 셋)
        List<Tuple> ratingAndReviewCount = reviewRepository.findReviewStatsByHostIds(hostIds);

        System.out.println("리뷰 평점과 리뷰 몇 명이 남겼는지 가져오기=" + ratingAndReviewCount);

        // 가격엔티티 가져오기
        List<Price> priceList = priceRepository.findAvgPriceByHostIds((List<Integer>) availableHostIds);
        System.out.println("가격 가져오기=" + priceList);

        //호스트별(예약 가능한 호스트) 평균가격(ResultAccommodationsDTO에 셋)
        List<HostAvgPriceDTO> avgPrice = AvgPrice(priceList, filterDTO.getStartEndDay());
        System.out.println("평균 가격=" + avgPrice);

        // 3. 이미지 가져오기(ResultAccommodationsDTO에 셋)
        List<Tuple> imageNames = imageRepository.findImageNamesByHostIds(hostIds);


        // ResultAccommodationsDTO 생성하여 필요한 데이터 세팅
        Map<Integer, ResultAccommodationsDTO> resultMap = new HashMap<>();

        // 1. 호스트 기본 정보를 세팅
        for (Tuple hostInfoTuple : hostInfos) {
            Integer hostId = hostInfoTuple.get(hostInfo.id);
            ResultAccommodationsDTO resultAccommodationsDTO = new ResultAccommodationsDTO();
            resultAccommodationsDTO.setHIdx(hostId);
            resultAccommodationsDTO.setHCate(HCate.getHCateTypeInKorean(hostInfoTuple.get(hostInfo.hCate).ordinal()));
            resultAccommodationsDTO.setHName(hostInfoTuple.get(hostInfo.hname));
            resultAccommodationsDTO.setXAxis(hostInfoTuple.get(hostInfo.xAxis));
            resultAccommodationsDTO.setYAxis(hostInfoTuple.get(hostInfo.yAxis));
            resultMap.put(hostId, resultAccommodationsDTO);
        }

        // 2. 리뷰 평점 및 리뷰 갯수를 세팅
        for (Tuple reviewTuple : ratingAndReviewCount) {
            Integer hostId = reviewTuple.get(review.hostInfo.id);
            ResultAccommodationsDTO resultAccommodationsDTO = resultMap.get(hostId);

            if (resultAccommodationsDTO != null) {
                // 정확한 형식을 사용하여 값을 가져오기
                Double avgRating = reviewTuple.get(review.revRating.avg()); // 평균 평점 가져오기
                Long reviewCount = reviewTuple.get(review.revRating.countDistinct()); // 리뷰 수 가져오기

                // Null pointer exception 예방을 위한 null 체크
                if (avgRating != null) {
                    resultAccommodationsDTO.setRating(avgRating);
                }

                if (reviewCount != null) {
                    resultAccommodationsDTO.setReviewCount(Math.toIntExact(reviewCount));
                }
            }
        }

        // 3. 평균 가격을 세팅
        for (HostAvgPriceDTO priceDTO : avgPrice) {
            Integer hostId = priceDTO.getHostIdx();
            ResultAccommodationsDTO resultAccommodationsDTO = resultMap.get(hostId);

            if (resultAccommodationsDTO != null) {
                resultAccommodationsDTO.setOneDayPrice(priceDTO.getAvgPrice());
            }
        }

        // 4. 이미지 이름을 세팅
        for (Tuple imageTuple : imageNames) {
            Integer hostId = imageTuple.get(hostInfo.id);
            ResultAccommodationsDTO resultAccommodationsDTO = resultMap.get(hostId);

            if (resultAccommodationsDTO != null) {
                resultAccommodationsDTO.setImg_url(imageTuple.get(image.imgName.fName));
            }
        }

        // 최종 결과 리스트로 반환
        return new ArrayList<>(resultMap.values());
    }

    //숙소 검색 필터링
    public List<ResultAccommodationsDTO> filteringAccommodations(FilterDTO filterDTO) {
        //4. 가져와진 호스트 인포

        return null;
    }

    //숙소 검색 필터링(roomInfo.id 리스트 반환)
    private List<Tuple> searchKeyword(FilterDTO filterDTO) {

        //1. 검색어 필터링(roomInfo.id 리스트)
        List<Integer> keyword = hostInfoRepository.keywordFiltering(filterDTO.getKeyword());

        //2. 검색어의 해당하는 리스트중 해당 인원수 이상의 숙소만 필터링(roomInfo.id 리스트)
        return  roomInfoRepository.personFiltering(filterDTO.getPerson(), keyword);
    }

    public List<HostAvgPriceDTO> AvgPrice(List<Price> priceList, @NotNull StartEndVo startEndDay) {
        LocalDateTime checkIn = startEndDay.getStart();
        LocalDateTime checkOut = startEndDay.getEnd();

        // 가격 리스트를 순회하며 각 호스트의 평균가격을 계산하고, HostAvgPriceDTO 리스트로 변환
        return priceList.stream()
                .map(price -> {
                    Integer avgPrice = calculateAveragePrice(price, checkIn, checkOut);
                    // HostAvgPriceDTO 생성
                    return new HostAvgPriceDTO(price.getHostInfo().getId(), avgPrice);
                })
                .collect(Collectors.toList());
    }

    private int calculateAveragePrice(Price price, LocalDateTime checkIn, LocalDateTime checkOut) {
        int totalDays = 0; // 총 날짜 수
        int totalPrice = 0; // 총 가격
        LocalDateTime current = checkIn; // 현재 날짜를 체크인 날짜로 초기화

        // 체크인 날짜부터 체크아웃 날짜 전까지 순회
        while (!current.isEqual(checkOut)) {
            // 하루 가격을 계산하여 총 가격에 더한다
            int dailyPrice = calculateDailyPrice(price, current);
            totalPrice += dailyPrice;
            current = current.plusDays(1); // 다음 날짜로 이동
            totalDays++; // 총 날짜 수 증가
        }

        // totalDays가 0일 경우를 대비
        if (totalDays == 0) {
            return 0; // 예외 또는 기본값 설정 가능
        }


        // 평균 가격을 계산하여 반환
        return totalPrice / totalDays;
    }

    private int calculateDailyPrice(Price price, LocalDateTime current) {
        // 피크 가격 계산
        if (price.getPeakVo() != null && isWithinRange(current, price.getPeakVo().getPeakStart(), price.getPeakVo().getPeakEnd())) {
            return price.getPeakVo().getPeakPrice();
        }
        // 세미피크 가격 계산
        else if (price.getSemi() != null && isWithinRange(current, price.getSemi().getSemiStart(), price.getSemi().getSemiEnd())) {
            return price.getSemi().getSemiPrice();
        }
        // 세미피크와 피크가 설정되지 않은 경우 정규 가격 반환
        else {
            return price.getRegularPrice();
        }
    }

    private boolean isWithinRange(LocalDateTime current, LocalDateTime start, LocalDateTime end) {
        if (current == null) {
            return false; // `current`가 null이면 범위에 포함되지 않음
        }

        boolean afterOrEqualStart = start == null || current.isEqual(start) || current.isAfter(start);
        boolean beforeOrEqualEnd = end == null || current.isEqual(end) || current.isBefore(end);

        return afterOrEqualStart && beforeOrEqualEnd;
    }
}