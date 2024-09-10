package com.omakase.omastay.service;

import com.omakase.omastay.repository.FacilitiesRepository;
import com.omakase.omastay.repository.HostInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilitiesService {

    @Autowired
    private FacilitiesRepository facilitiesRepository;

    @Autowired
    private HostInfoRepository hostInfoRepository;

}
