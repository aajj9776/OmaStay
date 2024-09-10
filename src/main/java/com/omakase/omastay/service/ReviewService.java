package com.omakase.omastay.service;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.ReviewMapper;
import com.omakase.omastay.repository.ReviewRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    
    public ReviewDTO addReview(ReviewDTO reviewDTO) {
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);
        System.out.println("얍"+reviewDTO);
        Member member = new Member();
        member.setId(1);
        review.setMember(member);

        Reservation reservation = new Reservation();
        reservation.setId(1);
        review.setReservation(reservation);

        review.setRevContent(reviewDTO.getRevContent());
        review.setRevDate(LocalDateTime.now());
        review.setRevNone(null);
        review.setRevRating(reviewDTO.getRevRating());
        review.setRevStatus(BooleanStatus.TRUE);
        review.setRevWriter("정한별");

        System.out.println("확인좀"+review);
        Review savedReview = reviewRepository.save(review);
        ReviewDTO savedReviewDTO = ReviewMapper.INSTANCE.toReviewDTO(savedReview);
        return savedReviewDTO;

        

    }
}