package com.omakase.omastay.service;

import com.omakase.omastay.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;


}
