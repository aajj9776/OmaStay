package com.omakase.omastay.service;

import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.temporal.ChronoUnit;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

 
    // 매주 월요일 00시에 추천 목록을 업데이트 (지난 주 일~토까지 체크인의 매출을 기준으로 추천 목록을 업데이트)
    // @Transactional 
    // @Scheduled(fixedRate = 1800000) // 30분 = 1800000 milliseconds
    // public void updateRecommendation() {
    //     int cnt =0;

    //     // 현재 시간에서 하루 전 날짜를 계산
    //     LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);

    //     // 하루가 지난 Reservation의 ID를 가져옴
    //     List<Reservation> expiredReservation = reservationRepository.findExpiredReservationsNotInSale(yesterday);

    //     // 필요한 로직을 처리 (예: Sale 테이블에 추가)
    //     for (Reservation reservation : expiredReservation) {
    //         Sales sales = new Sales();
    //         sales.setReservation(reservation);  // Sales 테이블에 예약 ID 설정
    //         sales.setSalDate(LocalDate.now());  // 현재 날짜 설정
    //         sales.setHostInfo(reservation.getRoomInfo().getHostInfo());
    //         salesRepository.save(sales); // Sales 테이블에 삽입
    //         cnt ++;
    //     }

    //     if(cnt > 0){
    //         System.out.println("매출 테이블에 "+cnt+"건 추가");
    //     }
    // }


}
