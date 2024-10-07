package com.omakase.omastay.repository;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.entity.AdminMember;

import com.omakase.omastay.repository.custom.AdminMemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AdminMemberRepository extends JpaRepository<AdminMember, Integer>, AdminMemberRepositoryCustom {

    int countByAdId(String adId);

    @Query("SELECT a FROM AdminMember a WHERE a.adId = :adId And a.adAuth=0")
    AdminMember findByAdId(@Param("adId") String adId);

}