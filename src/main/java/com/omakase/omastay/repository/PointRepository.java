package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Point;
import com.omakase.omastay.repository.custom.PointRepositoryCustom;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface PointRepository extends JpaRepository<Point, Integer>, PointRepositoryCustom {

    @Query("SELECT p FROM Point p JOIN p.member m WHERE m.id = :memIdx ORDER BY p.pDate DESC")
    List<Point> findPointsByMemberId(@Param("memIdx") int memIdx);

    //where이 p.memIdx = :memIdx 이고, 가장 최근의 1건 가져오는 쿼리
    @Query("SELECT p.pSum FROM Point p WHERE p.member.id = :memIdx ORDER BY p.pDate DESC")
    Integer getSumPoint(@Param("memIdx") int memIdx); // 속성 이름이 mIdx인지 확인

    @Query("SELECT p.pSum FROM Point p WHERE p.member.id = :memIdx ORDER BY p.pDate DESC")
    List<Integer> findLatestPointSumByMemberId(@Param("memIdx") int memIdx);
    
    
    



}