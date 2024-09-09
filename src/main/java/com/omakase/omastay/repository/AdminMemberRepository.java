package com.omakase.omastay.repository;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.entity.AdminMember;

import com.omakase.omastay.repository.custom.AdminMemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminMemberRepository extends JpaRepository<AdminMember, Integer>, AdminMemberRepositoryCustom {

    int countByAdId(String adId);

    AdminMember findByAdId(String adId);
}