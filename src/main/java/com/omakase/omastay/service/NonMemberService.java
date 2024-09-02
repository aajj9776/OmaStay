package com.omakase.omastay.service;

import com.omakase.omastay.repository.NonMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonMemberService {

    @Autowired
    private NonMemberRepository nonMemberRepository;


}
