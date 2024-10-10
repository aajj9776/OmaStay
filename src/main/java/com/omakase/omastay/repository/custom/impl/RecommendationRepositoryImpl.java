package com.omakase.omastay.repository.custom.impl;

import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDate;

import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.entity.QHostInfo;
import com.omakase.omastay.entity.QPayment;
import com.omakase.omastay.entity.QReservation;
import com.omakase.omastay.entity.QReview;
import com.omakase.omastay.entity.Recommendation;

import static com.omakase.omastay.entity.QSales.sales;
import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RecommendationRepositoryImpl implements RecommendationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RecommendationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    // public List<Recommendation> getTypeRecommendations(HCate Type){

    //     BooleanBuilder builder = new BooleanBuilder();
    //     builder.and(recommendation.hostInfo.hCate.eq(Type));

    //     return queryFactory
    //             .select(Projections.constructor( // Top5SalesDTO 생성자 사용
    //                     Top5SalesDTO.class,
    //                     hi.hname.as("hostName"),
    //                     payment.nsalePrice.castToNum(Integer.class).sum().as("totalSales")
    //             ))
    //             .from(recommendation)
    //             .join(recommendation.hostInfo, hostInfo)
    //             .join(sales.reservation, reservation)
    //             .join(reservation.roomInfo, roomInfo) // roomInfo 조인 추가
    //             .join(reservation.payment, payment)
    //             .where(builder)
    //             .groupBy(recommendation.hostInfo.hname)
    //             .orderBy(payment.nsalePrice.castToNum(Integer.class).sum().desc())
    //             .limit(5)
    //             .fetch();
    // }

    // public List<Top5SalesDTO> findTop5SalesByRegion(String region){

    //     BooleanBuilder builder = new BooleanBuilder();
    //     builder.and(sales.salDate.month().eq(LocalDate.now().getMonthValue()));
    //     builder.and(payment.payStatus.eq(PayStatus.PAY));

    //     if (region != null) {
    //         builder.and(hostInfo.region.eq(region)); 
    //     }

    //     return queryFactory
    //             .select(Projections.constructor( // Top5SalesDTO 생성자 사용
    //                     Top5SalesDTO.class,
    //                     hostInfo.hname.as("hostName"),
    //                     payment.nsalePrice.castToNum(Integer.class).sum().as("totalSales")
    //             ))
    //             .from(sales)
    //             .join(sales.hostInfo, hostInfo)
    //             .join(sales.reservation, reservation)
    //             .join(reservation.roomInfo, roomInfo) // roomInfo 조인 추가
    //             .join(reservation.payment, payment)
    //             .where(builder)
    //             .groupBy(hostInfo.hname)
    //             .orderBy(payment.nsalePrice.castToNum(Integer.class).sum().desc())
    //             .limit(5)
    //             .fetch();
    // }

    // 관리자 추천 숙소 - 숙소 유형에 해당하는 호스트별로 매출 상위 5개를 가져오는 쿼리
    @Override
    public List<Recommendation> getRecommendationsWeeklyByHCate(HCate hCate, LocalDate startDate, LocalDate endDate) {
        QHostInfo hostInfo = QHostInfo.hostInfo;
        QReservation reservation = QReservation.reservation;
        QPayment payment = QPayment.payment;
        QReview review = QReview.review;

        startDate = LocalDate.now().minusMonths(1);
        
        return queryFactory
            .select(Projections.constructor(
                Recommendation.class, 
                hostInfo,
                hostInfo.hCate,
                payment.nsalePrice.castToNum(Integer.class).sum(),
                Expressions.constant(LocalDateTime.now()))
            )
            .from(sales)
            .join(sales.hostInfo, hostInfo)
            .join(sales.reservation, reservation)
            .join(reservation.payment, payment) 
            //.leftJoin(review).on(review.hostInfo.eq(hostInfo))
            .where(
                hostInfo.hname.isNotNull(),  // hname이 null이 아닌 조건
                hostInfo.hCate.eq(hCate),    // hCate가 현재 enum 값인 조건 추가
                sales.salDate.between(startDate, endDate) // 날짜 범위 조건 추가
            )
            .groupBy(hostInfo.id)  
            //.having(review.revRating.avg().goe(4.0))  // 평점이 4.0 이상인 호스트만 추천
            .orderBy(payment.nsalePrice.castToNum(Integer.class).sum().desc())  // 총 매출로 정렬
            .limit(5)  // 상위 5개 제한 
            .fetch();
    }   
}
