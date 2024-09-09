package com.omakase.omastay.service;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.AdminMemberMapper;
import com.omakase.omastay.repository.AdminMemberRepository;
import com.omakase.omastay.vo.UserProfileVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int hostidcheck(String adId){

        int cnt = adminMemberRepository.countByAdId(adId);

        return cnt;
    }

    public boolean hostregist(String id, String pw, String email) {
        try {
            adminMemberRepository.save(AdminMember.builder()
                    .adId(id)
                    .adminProfile(UserProfileVo.builder()
                            .pw(passwordEncoder.encode(pw))
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

    public AdminMemberDTO hostlogin(String id, String pw) {
        AdminMember adminMember = adminMemberRepository.findByAdId(id);
        if (adminMember != null) {
            if (passwordEncoder.matches(pw, adminMember.getAdminProfile().getPw())) {
                return AdminMemberMapper.INSTANCE.toAdminMemberDTO(adminMember);
            }
        }
        return null;
    }

    public boolean hostfindpw(String id, String email) {
        AdminMember adminMember = adminMemberRepository.findByAdId(id);
        if (adminMember != null) {
            if (email.equals(adminMember.getAdminProfile().getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean hostchagepw(String id, String email, String pw) {

        AdminMember adminMember = adminMemberRepository.findByAdId(id);
        if (adminMember != null) {
            if (email.equals(adminMember.getAdminProfile().getEmail())) {
                adminMember.getAdminProfile().setPw(passwordEncoder.encode(pw));
                adminMemberRepository.save(adminMember);
                return true;
            }
        }
        
        return false;
    }

}
