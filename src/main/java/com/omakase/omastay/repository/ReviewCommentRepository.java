package com.omakase.omastay.repository;
import java.util.List;

import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.repository.custom.ReviewCommentRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer>, ReviewCommentRepositoryCustom {

    List<ReviewComment> findAll();

    @Query("SELECT r FROM ReviewComment r WHERE r.review = :review AND rcStatus = 0")
    ReviewComment findByReview(@Param("review")Review review);


}