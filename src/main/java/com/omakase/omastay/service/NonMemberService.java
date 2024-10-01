package com.omakase.omastay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.NonMemberDTO;
import com.omakase.omastay.entity.NonMember;
import com.omakase.omastay.mapper.NonMemberMapper;
import com.omakase.omastay.repository.NonMemberRepository;

import jakarta.transaction.Transactional;

@Service
public class NonMemberService {

    @Autowired
    private NonMemberRepository nonMemberRepository;



    @Transactional
    public NonMemberDTO insertNonMember(NonMemberDTO nonMember){
        NonMember res = NonMemberMapper.INSTANCE.toNonMember(nonMember);
        res.setNonPhone(null);
        NonMember result = nonMemberRepository.save(res);
        return NonMemberMapper.INSTANCE.toNonMemberDTO(result);
    }


}
