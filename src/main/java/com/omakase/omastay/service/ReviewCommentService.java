package com.omakase.omastay.service;

import com.omakase.omastay.dto.ReviewCommentDTO;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.ReviewCommentMapper;
import com.omakase.omastay.mapper.ReviewMapper;
import com.omakase.omastay.repository.ReviewCommentRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommentService {

    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    public void regRevComment(ReviewDTO reviewDTO, String rcComment){
        
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);
        ReviewComment reviewComment = reviewCommentRepository.findByReview(review);
        if(reviewComment == null){
            reviewComment = new ReviewComment();
        }
        reviewComment.setReview(review);
        reviewComment.setRcComment(rcComment);
        reviewComment.setRcDate(LocalDateTime.now());
        reviewComment.setRcStatus(BooleanStatus.TRUE);

        reviewCommentRepository.save(reviewComment);
    }

    public ReviewCommentDTO getRevComment(ReviewDTO reviewDTO){
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);
        ReviewComment reviewComment = reviewCommentRepository.findByReview(review);
        return ReviewCommentMapper.INSTANCE.toReviewCommentDTO(reviewComment);
    }

    public void delRevComment(ReviewDTO reviewDTO){
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);
        ReviewComment reviewComment = reviewCommentRepository.findByReview(review);
        reviewComment.setRcStatus(BooleanStatus.FALSE);

        reviewCommentRepository.save(reviewComment);
    }


}
