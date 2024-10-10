package com.omakase.omastay.service;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.ReviewMapper;
import com.omakase.omastay.mapper.ServiceMapper;
import com.omakase.omastay.repository.ReviewRepository;
import com.omakase.omastay.util.FileRenameGcs;
import com.omakase.omastay.vo.FileImageNameVo;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import org.eclipse.angus.mail.imap.protocol.FLAGS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    
    public ReviewDTO addReview(ReviewDTO reviewDTO, List<String> onames, List<String> fnames) {
        Review review = ReviewMapper.INSTANCE.toReview(reviewDTO);
        Member member = new Member();
        member.setId(1);
        review.setMember(member);

        Reservation reservation = new Reservation();
        reservation.setId(20);
        review.setReservation(reservation);

        HostInfo hostInfo = new HostInfo();
        hostInfo.setId(1);
        review.setHostInfo(hostInfo);

        review.setRevDate(LocalDateTime.now());
        review.setRevNone(null);
        review.setRevStatus(BooleanStatus.TRUE);
        review.setRevWriter("정한별");
        
        
        ReviewDTO result = null;
    
        // 저장된 파일명과 원본 파일명을 DTO에 추가 (선택사항)
        FileImageNameVo vo = new FileImageNameVo();
        String allFnames = String.join(",", fnames);
        String allOnames = String.join(",", onames);
        vo.setFName(allFnames);
        vo.setOName(allOnames);
        review.setRevFileImageNameVo(vo);
        Review dto =  reviewRepository.save(review);

        ReviewDTO res = ReviewMapper.INSTANCE.toReviewDTO(dto);

        return res;
    
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

    // 호스트 리뷰 상세보기
    public ReviewDTO getReview(int id) {
        Review review  = reviewRepository.findById(id).get();
        return ReviewMapper.INSTANCE.toReviewDTO(review);
    }

    //호스트 오늘 리뷰 가져오기
    public List<ReviewDTO> getReviewDay(HostInfoDTO hostInfoDTO) {

        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);

        LocalDateTime date = LocalDate.now().atStartOfDay();

        List<Review> review = reviewRepository.findReviewByDate(date, hostInfo);

        return ReviewMapper.INSTANCE.toReviewDTOList(review);
    }

    //호스트 이번주 리뷰 가져오기
    public List<ReviewDTO> getReviewWeek(HostInfoDTO hostInfoDTO) {

        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);

        LocalDateTime now =LocalDate.now().atStartOfDay();
        LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<Review> review = reviewRepository.findReviewByWeek(startOfWeek, endOfWeek, hostInfo);

        return ReviewMapper.INSTANCE.toReviewDTOList(review);
    }

    //호스트 이번달 리뷰 가져오기
    public List<ReviewDTO> getReviewMonth(HostInfoDTO hostInfoDTO) {

        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);

        LocalDateTime now = LocalDate.now().atStartOfDay();
        LocalDateTime startOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime endOfMonth = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);

        List<Review> review = reviewRepository.findReviewByMonth(startOfMonth, endOfMonth, hostInfo);

        return ReviewMapper.INSTANCE.toReviewDTOList(review);
    }


}
    
