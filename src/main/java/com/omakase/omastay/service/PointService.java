package com.omakase.omastay.service;

import com.omakase.omastay.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;


}
