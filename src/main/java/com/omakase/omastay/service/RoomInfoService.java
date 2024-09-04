package com.omakase.omastay.service;

import com.omakase.omastay.repository.RoomInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomInfoService {

    @Autowired
    private RoomInfoRepository roomInfoRepository;


}
