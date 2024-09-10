package com.omakase.omastay.service;

import com.omakase.omastay.repository.HostFacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostFacilitiesService {

    @Autowired
    private HostFacilitiesRepository hostFacilitiesRepository;


}
