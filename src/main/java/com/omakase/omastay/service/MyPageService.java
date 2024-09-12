package com.omakase.omastay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.mapper.MemberMapper;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.repository.ReservationRepository;
import java.util.Optional;

@Service
public class MyPageService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    public MemberDTO getMemberInfo(int memberId) {

        Optional<Member> res = memberRepository.findById(memberId);

        MemberDTO memberDTO = MemberMapper.INSTANCE.toMemberDTO(res.get());

        return memberDTO;
    }


    
}
