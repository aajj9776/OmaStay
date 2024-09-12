package com.omakase.omastay.service;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.mapper.FacilitiesMapper;
import com.omakase.omastay.repository.FacilitiesRepository;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.RoomInfoRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FacilitiesService {
    @Autowired
    private HostInfoRepository hostInfoRepository;

    @Autowired
    private RoomInfoRepository roomInfoRepository;

    @Autowired
    private JPAQueryFactory queryFactory;

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    public List<FacilitiesDTO> all() {

        List<Facilities> facilities = facilitiesRepository.findAll();

        return FacilitiesMapper.INSTANCE.toFacilitiesDTOList(facilities);
    }

    public List<ResultAccommodationsDTO> filteringAccommodations(FilterDTO filterDTO) {

        //1. 검색어 필터링(hostInfo에서 가져와야됨)
        List<Integer> keyword = hostInfoRepository.keywordFiltering(filterDTO.getKeyword());

        //2. 검색어의 해당하는 리스트중 해당 날짜 예약이 가능한 숙소만 필터링(reservation에서 가져와야됨)
        Set<Integer> date = roomInfoRepository.dateFiltering(filterDTO.getStartEndDay(), keyword);

        //3. 검색어 + 해당 날짜중 해당 인원이 들어갈 수 있는 숙소만 필터링(roomInfo에서 가져와야됨)
        Set<HostInfo> person = roomInfoRepository.personFiltering(filterDTO.getPerson(), date);

        //위의 3개의 조건은 And연산
        return null;
    }

}
