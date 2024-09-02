package com.omakase.omastay.service;


import com.omakase.omastay.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;


}
