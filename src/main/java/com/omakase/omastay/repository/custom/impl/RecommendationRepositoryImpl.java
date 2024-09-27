package com.omakase.omastay.repository.custom.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.threeten.bp.LocalDate;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.QHostInfo;
import com.omakase.omastay.entity.QPayment;
import com.omakase.omastay.entity.QRecommendation;
import com.omakase.omastay.entity.QReservation;
import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.entity.QSales;
import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RecommendationRepositoryImpl implements RecommendationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    QHostInfo hostInfo = QHostInfo.hostInfo;
    public static final QSales sales = QSales.sales;
    public static final QReservation reservation = QReservation.reservation;
    public static final QRoomInfo roomInfo = QRoomInfo.roomInfo;

    public RecommendationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    final static QRecommendation recommendation = QRecommendation.recommendation;

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
}
