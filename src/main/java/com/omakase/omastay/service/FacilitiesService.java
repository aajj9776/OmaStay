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
import java.util.Set;
import java.util.stream.Collectors;

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
        // 검색어 필터링 된 룸인포 키값 리스트 가져오기
        List<Integer> roomIdxs = searchKeyword(filterDTO);

        //호스트 키값이랑 호텔 이름, x좌표, y좌표 가져오기
        Set<Tuple> hostInfos = roomInfoRepository.findHostIdsByRoomIds(roomIdxs);

        //튀플에서 호스트 키값만 가져오기
        List<Integer> hostIds = roomInfoRepository.findHostIdsByRoomIds(roomIdxs).stream()
                .map(tuple -> tuple.get(QRoomInfo.roomInfo.hostInfo.id))
                .collect(Collectors.toList());

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

        List<Integer> roomIdxs = searchKeyword(filterDTO); //1. 검색 필터링

        //4. 가져와진 호스트 인포

        return null;
    }

    //숙소 검색 필터링(roomInfo.id 리스트 반환)
    private List<Integer> searchKeyword(FilterDTO filterDTO) {

        //1. 검색어 필터링(roomInfo.id 리스트)
        List<Integer> keyword = hostInfoRepository.keywordFiltering(filterDTO.getKeyword());

        //2. 검색어의 해당하는 리스트중 해당 날짜 예약이 가능한 숙소만 필터링(roomInfo.id 리스트)
        Set<Integer> date = roomInfoRepository.dateFiltering(filterDTO.getStartEndDay(), keyword);

        //3. 검색어의 해당하는 리스트중 해당 인원수 이상의 숙소만 필터링(roomInfo.id 리스트)
        return roomInfoRepository.personFiltering(filterDTO.getPerson(), date);
    }
}
