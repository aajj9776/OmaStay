package com.omakase.omastay.service;

import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.repository.FacilitiesRepository;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.ReservationRepository;
import com.omakase.omastay.repository.RoomInfoRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class FacilitiesService {

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    private HostInfoRepository hostInfoRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Autowired
    private JPAQueryFactory queryFactory;


    public List<ResultAccommodationsDTO> filteringAccommodations(FilterDTO filterDTO) {

        //1. 검색어 필터링(hostInfo에서 가져와야됨)
        Set<HostInfo> keyword = hostInfoRepository.keywordFiltering(filterDTO.getKeyword());

        //2. 검색어의 해당하는 리스트중 해당 날짜 예약이 가능한 숙소만 필터링(reservation에서 가져와야됨)
        Set<HostInfo> date = reservationRepository.dateFiltering(filterDTO.getStartDate(), filterDTO.getEndDate());


        //3. 검색어 + 해당 날짜중 해당 인원이 들어갈 수 있는 숙소만 필터링(roomInfo에서 가져와야됨)
        Set<HostInfo> person = roomInfoRepository.personFiltering(filterDTO.get());

        //위의 3개의 조건은 And연산
        return null;
    }



}
