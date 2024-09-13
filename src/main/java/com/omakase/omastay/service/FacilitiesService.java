package com.omakase.omastay.service;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.mapper.FacilitiesMapper;
import com.omakase.omastay.repository.*;
import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<ResultAccommodationsDTO> search(FilterDTO filterDTO) {
        // 검색어 필터링 된 룸인포 키값과 호스트인포 키값 리스트 가져오기(예약이 불가능한 객실 포함)
        List<Tuple> allHostIds = searchKeyword(filterDTO);

        //튜플에서 룸인포 키값만 가져오기
        List<Integer> roomIdxs = allHostIds.stream()
                .map(tuple -> tuple.get(roomInfo.id))
                .collect(Collectors.toList());

        //검색어의 해당하는 리스트중 해당 날짜 예약이 가능한 룸인포 키값과 호스트인포 키값 필터링(roomInfo.id 리스트)
        List<Tuple> availableHostIds = roomInfoRepository.dateFiltering(filterDTO.getStartEndDay(), roomIdxs);


        //호스트 키값이랑 호텔 이름, x좌표, y좌표 가져오기
        Set<Tuple> hostInfos = roomInfoRepository.findHostIdsByRoomIds(roomIdxs);

        //튜플에서 호스트 키값만 가져오기
        List<Integer> hostIds = roomInfoRepository.findHostIdsByRoomIds(roomIdxs).stream()
                .map(tuple -> tuple.get(roomInfo.hostInfo.id))
                .collect(Collectors.toList());

        // 예약 가능한 룸 ID 추출
        Set<Integer> availableRoomIds = availableHostIds.stream()
                .map(tuple -> tuple.get(roomInfo.id))
                .collect(Collectors.toSet());

        // 호스트별로 룸을 매핑
        Map<Integer, List<Tuple>> hostToRoomsMap = allHostIds.stream()
                .collect(Collectors.groupingBy(tuple -> tuple.get(roomInfo.hostInfo.id)));

        // 예약 불가능한 호스트 ID 추출
        Set<Integer> unavailableHostIds = hostToRoomsMap.entrySet().stream()
                .filter(entry -> entry.getValue().stream()
                        .map(tuple -> tuple.get(roomInfo.id))
                        .allMatch(roomId -> !availableRoomIds.contains(roomId)))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        //1. 리뷰 평점과 리뷰 몇명이 남겼는지 가져오기
        List<Tuple> ratingAndReviewCount = reviewRepository.findReviewStatsByHostIds(hostIds);

        //2. 평균 가격 가져오기
        List<Tuple> avgPrice = priceRepository.findAvgPriceByHostIds(hostIds, filterDTO.getStartEndDay());

        //3. 이미지 가져오기
        List<String> imageNames = imageRepository.findImageNamesByHostIds(hostIds);

        return null;
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
}
