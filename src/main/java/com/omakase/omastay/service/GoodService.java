package com.omakase.omastay.service;

import com.omakase.omastay.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;


}
