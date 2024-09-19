package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Member;
import com.omakase.omastay.repository.custom.MemberRepositoryCustom;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {

    Member findByMemberProfileEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.grade.gCate = :grade")
    List<Member> findByGrade(@Param("grade") String grade);

    @Query("SELECT m FROM Member m WHERE m.memberProfile.email = :email")
    Member findByEmail(@Param("email") String email);
}