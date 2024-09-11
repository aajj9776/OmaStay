package com.omakase.omastay.service;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.mapper.FacilitiesMapper;
import com.omakase.omastay.repository.FacilitiesRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilitiesService {

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    public List<FacilitiesDTO> all() {

        List<Facilities> facilities = facilitiesRepository.findAll();

        return FacilitiesMapper.INSTANCE.toFacilitiesDTOList(facilities);
    }


}
