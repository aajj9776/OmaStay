package com.omakase.omastay.service;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.ImageMapper;
import com.omakase.omastay.mapper.ReviewMapper;
import com.omakase.omastay.repository.ReviewRepository;
import com.omakase.omastay.vo.FileImageNameVo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Integer addReview(ReviewDTO reviewDTO, List<String> onames, List<String> fnames) {
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);

        Reservation reservation = new Reservation();
        reservation.setId(56);
        review.setReservation(reservation);

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

      public List<Review> findAllReview(String sortOption,Integer hIdx){
        
        return reviewRepository.findAll(sortOption,hIdx);
   
    }


     // 호스트 전체 리뷰 가져오기
    public List<ReviewDTO> getAllReview(HostInfoDTO hostInfoDTO) {

        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);

        List<Review> review = reviewRepository.findByHostInfoAndRevStatus(hostInfo, BooleanStatus.TRUE);

        return ReviewMapper.INSTANCE.toReviewDTOList(review);
    }

    // 호스트 리뷰 검색
    public List<ReviewDTO> searchHostReview(String type, String keyword, int hIdx){

        List<Review> review = reviewRepository.searchHostReview(type, keyword, hIdx);
 
        return ReviewMapper.INSTANCE.toReviewDTOList(review);
    }

    // 게시글 id 값으로 serviceDTO 가져오기
    public ReviewDTO getReview(int id) {
        Review review  = reviewRepository.findById(id).get();
        return ReviewMapper.INSTANCE.toReviewDTO(review);
    }

    public List<Object[]> getResCounts(){
        return reviewRepository.countReservationsByMemIdx();
    }

    public List<ReviewDTO> getAllReviewImages(Integer hIdx) {
        List<Review> reviewImage = reviewRepository.findByReviewAndImage(hIdx);
    return ReviewMapper.INSTANCE.toReviewDTOList(reviewImage);
    }

    

   
}

    
