package com.omakase.omastay.service;

import com.omakase.omastay.repository.AdminMemberRepository;
import com.omakase.omastay.repository.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

    @Autowired
    private AdminMemberRepository adminMemberRepository;

    public int hostidcheck(String adId){

        int cnt = adminMemberRepository.countByAdId(adId);

        return cnt;
    }


}
