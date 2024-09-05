package com.omakase.omastay.service;

import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.AdminMemberRepository;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.vo.UserProfileVo;

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

    public boolean hostregist(String id, String pw, String email) {
        try {
            adminMemberRepository.save(AdminMember.builder()
                    .adId(id)
                    .adminProfile(UserProfileVo.builder()
                            .pw(pw)
                            .email(email)
                            .status(BooleanStatus.TRUE)
                            .build())
                    .adAuth(0)
                    .build());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
