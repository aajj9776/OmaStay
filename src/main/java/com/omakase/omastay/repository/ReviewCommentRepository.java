package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.repository.custom.ReviewCommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer>, ReviewCommentRepositoryCustom {

    ReviewComment findByReview(Review review);

}