package com.omakase.omastay.repository;
 
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
            @Query("SELECT r FROM Review r " +
            "JOIN FETCH r.member m " +
            "JOIN FETCH m.grade g " +
            "JOIN FETCH r.hostInfo h " +
            "JOIN FETCH r.reservation res " +
            "JOIN FETCH res.roomInfo room " + 
            "WHERE h.id = :hIdx " +
            "ORDER BY " +
            "CASE WHEN :sortOption = '추천순' THEN r.revDate END DESC, " +
            "CASE WHEN :sortOption = '최신순' THEN r.revDate END DESC, " +
            "CASE WHEN :sortOption = '평점 높은 순' THEN r.revRating END DESC, " +
            "CASE WHEN :sortOption = '평점 낮은 순' THEN r.revRating END ASC")
    List<Review> findAll(@Param("sortOption") String sortOption,@Param("hIdx") Integer hIdx);  


    List<Review> findByHostInfoAndRevStatus(HostInfo hostInfo, BooleanStatus revStatus);

    @Query("SELECT r.member.id, COUNT(r) FROM Reservation r GROUP BY r.member.id")
    List<Object[]> countReservationsByMemIdx();

     @Query("SELECT r FROM Review r WHERE r.hostInfo.id = :hIdx")
    List<Review> findByReviewAndImage(@Param("hIdx") Integer hIdx);



   
}
