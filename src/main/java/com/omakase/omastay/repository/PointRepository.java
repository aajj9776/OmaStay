package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Point;
import com.omakase.omastay.repository.custom.PointRepositoryCustom;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointRepository extends JpaRepository<Point, Integer>, PointRepositoryCustom {

    //관리자 포인트에서 포인트 내역을 가져오는 쿼리
    @Query("SELECT p FROM Point p JOIN FETCH p.member ORDER BY p.id desc")
    List<Point> findAll();

    //관리자 포인트 추가에서 회원 id를 받아 가장 최근의 포인트 1건을 가져오는 쿼리
    @Query("SELECT p FROM Point p WHERE p.member.id = :memIdx ORDER BY p.pDate DESC")
    Integer getSumPoint(@Param("memIdx") Integer memIdx); // 속성 이름이 mIdx인지 확인

    @Query("SELECT p FROM Point p WHERE p.member.id = :memIdx ORDER BY p.pDate DESC")
    List<Point> findByMemIdx(@Param("memIdx") Integer memIdx); 

    @Query("SELECT p.pSum FROM Point p WHERE p.member.id = :memIdx ORDER BY p.pDate DESC")
    List<Integer> findLatestPSumByMemIdx(@Param("memIdx") Integer memIdx);

    //관리자 포인트 추가에서 회원 id를 받아 가장 최근의 포인트 1건에서 잔여 포인트를 가져오는 쿼리
    @Query("SELECT p.pSum FROM Point p WHERE p.member.id = :memIdx AND p.pDate = (SELECT MAX(p2.pDate) FROM Point p2 WHERE p2.member.id = :memIdx)")
    List<Integer> getSumPoint(@Param("memIdx") int memIdx, Pageable pageable); // 속성 이름이 mIdx인지 확인

    @Query("SELECT p FROM Point p WHERE p.member.id = :memIdx AND p.id = :pIdx")
    Point findByIdAndMemIdx(@Param("pIdx") Integer pIdx, @Param("memIdx") Integer memIdx);

    @Query("SELECT p.pSum FROM Point p WHERE p.member.id = :memIdx ORDER BY p.pDate DESC")
    List<Integer> findLatestPointSumByMemberId(@Param("memIdx") int memIdx);

    @Query("SELECT p FROM Point p JOIN p.member m WHERE m.id = :memIdx ORDER BY p.pDate DESC")
    List<Point> findPointsByMemberId(@Param("memIdx") int memIdx);

}