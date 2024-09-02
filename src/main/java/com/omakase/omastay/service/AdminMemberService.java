package com.omakase.omastay.service;

import com.omakase.omastay.repository.AdminMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

    @Autowired
    private AdminMemberRepository adminMemberRepository;


}
