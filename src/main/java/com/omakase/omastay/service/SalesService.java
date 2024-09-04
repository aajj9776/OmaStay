package com.omakase.omastay.service;

import com.omakase.omastay.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;


}
