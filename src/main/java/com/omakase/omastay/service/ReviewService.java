package com.omakase.omastay.service;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.ReviewMapper;
import com.omakase.omastay.repository.ReviewRepository;
import com.omakase.omastay.util.FileRenameUtil;
import com.omakase.omastay.vo.FileImageNameVo;

import jakarta.persistence.EntityNotFoundException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.eclipse.angus.mail.imap.protocol.FLAGS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    
    public Integer addReview(ReviewDTO reviewDTO, List<String> onames, List<String> fnames) {
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);
        Member member = new Member();
        member.setId(1); 
        review.setMember(member);

        Reservation reservation = new Reservation();
        reservation.setId(2);
        review.setReservation(reservation);

        HostInfo hostInfo = new HostInfo();
        hostInfo.setId(1); 
        review.setHostInfo(hostInfo);

        review.setRevDate(LocalDateTime.now());
        review.setRevNone(null);
        review.setRevStatus(BooleanStatus.TRUE);
        review.setRevWriter("정한별"); 

        FileImageNameVo vo = new FileImageNameVo();
        String allFnames = String.join(",", fnames);
        String allOnames = String.join(",", onames);
        vo.setFName(allFnames);
        vo.setOName(allOnames);
        review.setRevFileImageNameVo(vo);

        Review savedReview = reviewRepository.save(review);

        return savedReview.getId();
    }

    public Review findById(Integer revIdx) {
        return reviewRepository.findById(revIdx)
                .orElseThrow(() -> new EntityNotFoundException("Review not found with id: " + revIdx)); 
    }

    public List<Review> findAllReview(){
        
        return reviewRepository.findAll();
   
    }




}
    
