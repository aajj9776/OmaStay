package com.omakase.omastay.service;

import com.omakase.omastay.repository.IqCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IqCommentService {

    @Autowired
    private IqCommentRepository iqCommentRepository;


}
