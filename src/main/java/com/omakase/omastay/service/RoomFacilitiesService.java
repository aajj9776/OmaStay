package com.omakase.omastay.service;

import com.omakase.omastay.repository.RoomFacilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomFacilitiesService {

    @Autowired
    private RoomFacilitiesRepository roomFacilitiesRepository;


}
