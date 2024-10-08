package com.omakase.omastay.service;
import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.custom.*;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.mapper.FacilitiesMapper;
import com.omakase.omastay.repository.*;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.Tuple;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDate;
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

    @Autowired
    HostFacilitiesRepository hostFacilitiesRepository;

    @Value("${upload}")
    private String realPath;

    public List<FacilitiesDTO> all() {

        List<Facilities> facilities = facilitiesRepository.findAll();

        return FacilitiesMapper.INSTANCE.toFacilitiesDTOList(facilities);
    }

    //숙소 검색 필터링
    @Transactional(readOnly = true)
    public AccommodationResponseDTO search(FilterDTO filterDTO, Pageable pageable, boolean isModal) {
        // 검색어 필터링 된 룸인포 키값과 호스트인포 키값 리스트 가져오기(예약이 불가능한 객실 포함)
        List<Tuple> allHostRoomIds = searchKeyword(filterDTO);

        System.out.println("검색어 필터링 후: " + allHostRoomIds);

        if (filterDTO.getFacilities() != null && !filterDTO.getFacilities().isEmpty()) {
            List<Integer> allHostIds = allHostRoomIds.stream()
                    .map(tuple -> tuple.get(hostInfo.id))
                    .distinct()
                    .toList();

            // 필요한 시설을 만족하는 호스트 ID 목록 조회
            List<Integer> validHostIds = hostFacilitiesRepository.findFacilitiesIdsByHostId(filterDTO.getFacilities(), allHostIds);

            // 유효한 호텔 ID를 가진 튜플 필터링
            allHostRoomIds = allHostRoomIds.stream()
                    .filter(tuple -> validHostIds.contains(tuple.get(hostInfo.id)))
                    .toList();
        }

        List<Integer> allHostIds = allHostRoomIds.stream()
                .map(tuple -> tuple.get(hostInfo.id))
                .distinct()
                .toList();


        // 가격엔티티 가져오기
        List<Price> priceList = priceRepository.findAvgPriceByHostIds(allHostIds);

        // 호스트별(예약 가능한 호스트) 평균가격(ResultAccommodationsDTO에 셋)
        List<HostAvgPriceDTO> avgPrice = AvgPrice(priceList, filterDTO.getStartEndDay());

        if (isPriceFilteringRequired(filterDTO)) {
            Integer startPrice = filterDTO.getStartPrice();
            Integer endPrice = filterDTO.getEndPrice();

            final int finalStartPrice = (startPrice != null) ? startPrice : 0;

            if (endPrice == null || endPrice == 500000) {
                // startPrice 이상의 가격만 필터링
                avgPrice = avgPrice.stream()
                        .filter(price -> price.getAvgPrice() >= finalStartPrice)
                        .collect(Collectors.toList());
            } else {
                // startPrice와 endPrice 범위 내의 가격만 필터링
                final int finalEndPrice = endPrice;
                avgPrice = avgPrice.stream()
                        .filter(price -> price.getAvgPrice() >= finalStartPrice && price.getAvgPrice() < finalEndPrice)
                        .collect(Collectors.toList());
            }

            List<Integer> avgPriceHostIds = avgPrice.stream()
                    .map(HostAvgPriceDTO::getHostIdx)
                    .toList();

            allHostRoomIds = allHostRoomIds.stream()
                    .filter(tuple -> avgPriceHostIds.contains(tuple.get(hostInfo.id)))
                    .toList();
        }

        // 튜플에서 룸인포 키값만 가져오기
        List<Integer> roomIdxs = allHostRoomIds.stream()
                .map(tuple -> tuple.get(roomInfo.id))
                .collect(Collectors.toList());

        // 검색어의 해당하는 리스트 중 해당 날짜 예약이 가능한 룸인포 키값과 호스트인포 키값 필터링(roomInfo.id 리스트)
        List<Tuple> availableHost = roomInfoRepository.dateFiltering(filterDTO.getStartEndDay(), roomIdxs);


        if (isPriceFilteringRequired(filterDTO))
        {
            // 예약 가능한 호스트 ID 추출
            List<Integer> availableHostIds = availableHost.stream()
                    .map(tuple -> tuple.get(hostInfo.id))
                    .distinct()
                    .toList();

            //예약 불가능 호스트 ID 추출
            List<Integer> unavailableHostIds = allHostIds.stream()
                    .filter(hostId -> !availableHostIds.contains(hostId))
                    .toList();

            //avgPrice에서 예약 불가능 호스트 ID를 가진 avgPrice의 가격을 null 처리
            avgPrice = avgPrice.stream()
                    .peek(price -> {
                        if (unavailableHostIds.contains(price.getHostIdx())) {
                            price.setAvgPrice(null);
                        }
                    })
                    .collect(Collectors.toList());
        }


        if (filterDTO.isSoldOut()) {
            roomIdxs = availableHost.stream()
                    .map(tuple -> tuple.get(roomInfo.id))
                    .collect(Collectors.toList());
        }


        // 호스트 키값이랑 호텔 이름, x좌표, y좌표 가져오기(ResultAccommodationsDTO에 셋)
        List<Tuple> hostInfos = roomInfoRepository.findHostsByRoomIds(roomIdxs);

        // 튜플에서 호스트 키값만 가져오기
        List<Integer> hostIds = hostInfos.stream()
                .map(tuple -> tuple.get(hostInfo.id))
                .collect(Collectors.toList());

        // 리뷰 평점과 리뷰 몇 명이 남겼는지 가져오기(ResultAccommodationsDTO에 셋)
        List<Tuple> ratingAndReviewCount = reviewRepository.findReviewStatsByHostIds(hostIds);

        // 이미지 가져오기
        List<Tuple> imageNames = imageRepository.findImageNamesByHostIds(hostIds);

        // ResultAccommodationsDTO 생성하여 필요한 데이터 세팅
        Map<Integer, ResultAccommodationsDTO> resultMap = new HashMap<>();

        // 1. 호스트 기본 정보를 세팅
        for (Tuple hostInfoTuple : hostInfos) {
            Integer hostId = hostInfoTuple.get(hostInfo.id);
            ResultAccommodationsDTO resultAccommodationsDTO = new ResultAccommodationsDTO();
            resultAccommodationsDTO.setHIdx(hostId);
            resultAccommodationsDTO.setHCate((hostInfoTuple.get(hostInfo.hCate)));
            resultAccommodationsDTO.setHCateKo(Objects.requireNonNull(hostInfoTuple.get(hostInfo.hCate)).getDescription());
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

            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);

            if (resultAccommodationsDTO != null) {
                resultAccommodationsDTO.setOneDayPrice(numberFormat.format(priceDTO.getAvgPrice()));
            }
        }



        // 4. 이미지 이름을 세팅
        for (Tuple imageTuple : imageNames) {
            Integer hostId = imageTuple.get(hostInfo.id);
            ResultAccommodationsDTO resultAccommodationsDTO = resultMap.get(hostId);

            if (resultAccommodationsDTO != null) {
                resultAccommodationsDTO.setImg_url(realPath + "host/" + imageTuple.get(image.imgName.fName));
            }
        }

        //모든 결과 리스트

        // 최종 결과 반환
        return paginateAccommodations(pageable, resultMap, isModal);
    }


    //숙소 검색 필터링(roomInfo.id 리스트 반환)
    @Transactional(readOnly = true)
    protected List<Tuple> searchKeyword(FilterDTO filterDTO) {

        //1. 검색어 필터링(roomInfo.id 리스트)
        List<Integer> keyword = hostInfoRepository.keywordFiltering(filterDTO);

        //2. 검색어의 해당하는 리스트중 해당 인원수 이상의 숙소만 필터링(roomInfo.id 리스트)
        return roomInfoRepository.personFiltering(filterDTO, keyword);
    }

    private AccommodationResponseDTO paginateAccommodations(Pageable pageable, Map<Integer, ResultAccommodationsDTO> resultMap, boolean isModal) {
        List<ResultAccommodationsDTO> resultAccommodationsDTOList = new ArrayList<>(resultMap.values());

        // 기본 페이지네이션 결과 생성
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), resultAccommodationsDTOList.size());
        Page<ResultAccommodationsDTO> resultPage = new PageImpl<>(resultAccommodationsDTOList.subList(start, end), pageable, resultAccommodationsDTOList.size());

        // 100개의 항목을 기준으로 한 페이지네이션 처리
        List<ResultAccommodationsDTO> limitedResultList = new ArrayList<>();

        // 첫 번째 페이지 시작 인덱스 그대로 사용
        int limitedPageEnd = start + 100; // 첫 번째 페이지 start + 100개의 항목
        limitedPageEnd = Math.min(limitedPageEnd, resultAccommodationsDTOList.size());

        if (start < resultAccommodationsDTOList.size()) {
            limitedResultList = resultAccommodationsDTOList.subList(start, limitedPageEnd);
        }

        // isModal이 true인 경우 제한된 결과만 반환
        if (isModal) {
            AccommodationResponseDTO modalResult = new AccommodationResponseDTO();
            modalResult.setAccommodationsMap(limitedResultList);
            return modalResult;
        }

        // Pagination 정보 설정 (기본 페이지네이션 기준)
        PageNation pageNation = new PageNation();
        pageNation.setPageNumber(resultPage.getNumber());
        pageNation.setPageSize(resultPage.getSize());
        pageNation.setTotalElements(resultPage.getTotalElements());
        pageNation.setTotalPages(resultPage.getTotalPages());
        pageNation.setLast(resultPage.isLast());

        // 페이징된 결과 추출
        List<ResultAccommodationsDTO> paginatedList = resultPage.getContent();

        // 최종 결과 설정
        AccommodationResponseDTO result = new AccommodationResponseDTO();
        result.setAccommodations(paginatedList);
        result.setPageNation(pageNation);
        result.setAccommodationsMap(limitedResultList); // 수정된 limitedResultList 설정
        return result;
    }

    public List<HostAvgPriceDTO> AvgPrice(List<Price> priceList, @NotNull StartEndVo startEndDay) {
        System.out.println("가격 리스트=" + priceList);
        System.out.println("체크인 날짜=" + startEndDay.getStart());
        System.out.println("체크아웃 날짜=" + startEndDay.getEnd());

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
        LocalDate current = checkIn.toLocalDate(); // 현재 날짜를 체크인 날짜로 초기화
        LocalDate endDate = checkOut.toLocalDate(); // 체크아웃 날짜의 날짜 부분

        // 체크인 날짜부터 체크아웃 날짜 전까지 순회
        while (!current.isEqual(endDate)) {
            int dailyPrice = calculateDailyPrice(price, current.atStartOfDay()); // LocalDateTime 변환 필요
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

    private boolean isPriceFilteringRequired(FilterDTO filterDTO) {
        Integer startPrice = filterDTO.getStartPrice();
        Integer endPrice = filterDTO.getEndPrice();

        if (startPrice != null && startPrice == 0 && endPrice != null && endPrice == 500000) {
            filterDTO.setStartPrice(null);
            filterDTO.setEndPrice(null);
        }

        return startPrice != null && endPrice != null;
    }
}
