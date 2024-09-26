package com.omakase.omastay.repository;
 
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;
import java.util.List;

import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {
        @Query("SELECT r FROM Review r " +
               "JOIN FETCH r.member m " +
               "JOIN FETCH m.grade g " +
               "JOIN FETCH r.hostInfo h " + 
               "JOIN FETCH r.reservation res " + 
               "JOIN FETCH res.roomInfo room") 
        List<Review> findAllReview(); 
    }