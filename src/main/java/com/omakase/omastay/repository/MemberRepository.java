package com.omakase.omastay.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.repository.custom.MemberRepositoryCustom;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>, MemberRepositoryCustom {


    //Member findByMemberProfileEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.grade.gCate = :grade")
    List<Member> findByGrade(@Param("grade") String grade);

    @Query("SELECT m FROM Member m WHERE m.memberProfile.email = :email")
    Member findByEmail(@Param("email") String email);

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.grade WHERE m.id = :memberId")
    Member findMemberWithGrade(@Param("memberId") int memberId);

    @Query("SELECT m FROM Member m LEFT JOIN FETCH m.reservations WHERE m.id = :memberId")
    Optional<Member> findMemberWithReservations(@Param("memberId") int memberId);

    @Query("SELECT m.grade.id FROM Member m WHERE m.id = :memberId")
    Integer findGIdxByMemberId(@Param("memberId") Integer memberId);
    

}