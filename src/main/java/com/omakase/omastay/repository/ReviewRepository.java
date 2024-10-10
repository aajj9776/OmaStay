package com.omakase.omastay.repository;
 
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

    List<Review> findByHostInfoAndRevStatus(HostInfo hostInfo, BooleanStatus revStatus);

    //오늘날짜 리뷰 조회
    @Query("SELECT r FROM Review r WHERE r.hostInfo = :hostInfo AND (r.revDate = :date AND r.revStatus = 0)")
    List<Review> findReviewByDate(@Param("date") LocalDateTime date, @Param("hostInfo") HostInfo hostInfo);

    //이번주 리뷰 조회
    @Query("SELECT r FROM Review r WHERE r.hostInfo = :hostInfo AND ((r.revDate <= :endOfWeek AND r.revDate >= :startOfWeek) AND r.revStatus = 0)")
    List<Review> findReviewByWeek(@Param("startOfWeek") LocalDateTime startOfWeek, @Param("endOfWeek") LocalDateTime endOfWeek, @Param("hostInfo") HostInfo hostInfo);

    //이번달 리뷰 조회\
    @Query("SELECT r FROM Review r WHERE r.hostInfo = :hostInfo AND ((r.revDate <= :endOfMonth AND r.revDate >= :startOfMonth) AND r.revStatus = 0)")
    List<Review> findReviewByMonth(@Param("startOfMonth") LocalDateTime startOfMonth, @Param("endOfMonth") LocalDateTime endOfMonth, @Param("hostInfo") HostInfo hostInfo);

    //리뷰 조회
    //@Query("SELECT r FROM Review r JOIN FETCH r.hostInfo h JOIN FETCH r.reservation res WHERE r.member.id = :memIdx AND r.revStatus = 0")
    //List<Review> findReviewsWithRoomAndHotelByMemberId(@Param("memIdx") int memIdx);
}