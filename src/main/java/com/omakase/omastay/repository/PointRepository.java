package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Point;
import com.omakase.omastay.repository.custom.PointRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PointRepository extends JpaRepository<Point, Integer>, PointRepositoryCustom {

    //where이 p.memIdx = :memIdx 이고, 가장 최근의 1건 가져오는 쿼리
    @Query("SELECT p.pSum FROM Point p WHERE p.member.id = :memIdx AND p.pDate = (SELECT MAX(p2.pDate) FROM Point p2 WHERE p2.member.id = :memIdx)")
    Integer getSumPoint(@Param("memIdx") int memIdx); // 속성 이름이 mIdx인지 확인
}