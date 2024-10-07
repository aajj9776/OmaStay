package com.omakase.omastay.repository;
import java.util.List;

import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer>, RecommendationRepositoryCustom {
    List<ReviewComment> findAll();

    ReviewComment findByReview(Review review);


}