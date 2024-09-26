package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.QHostInfo;
import com.omakase.omastay.entity.QPayment;
import com.omakase.omastay.entity.QReservation;
import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.entity.QSales;
import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import groovy.transform.Undefined.EXCEPTION;

import java.util.List;

import org.threeten.bp.LocalDate;

public class SalesRepositoryImpl implements SalesRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private QSales s = QSales.sales;
    private QHostInfo hi = QHostInfo.hostInfo;
    private QReservation r = QReservation.reservation;
    private QPayment p = QPayment.payment;
    private QRoomInfo ri = QRoomInfo.roomInfo;


    public SalesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Top5SalesDTO> findTop5SalesByRegion(String region){

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(s.salDate.month().eq(LocalDate.now().getMonthValue()));
        builder.and(p.payStatus.eq(PayStatus.PAY));

        if (region != null) {
            builder.and(hi.region.eq(region)); 
        }

        return queryFactory
                .select(Projections.constructor( // Top5SalesDTO 생성자 사용
                        Top5SalesDTO.class,
                        hi.hname.as("hostName"),
                        p.nsalePrice.castToNum(Integer.class).sum().as("totalSales")
                ))
                .from(s)
                .join(s.hostInfo, hi)
                .join(s.reservation, r)
                .join(r.roomInfo, ri) // roomInfo 조인 추가
                .join(r.payment, p)
                .where(builder)
                .groupBy(hi.hname)
                .orderBy(p.nsalePrice.castToNum(Integer.class).sum().desc())
                .limit(5)
                .fetch();
    }

    //Sales 테이블 검색
    @Override
    public List<Sales> searchSales(String startDate, String endDate, String region) {

        return queryFactory.selectFrom(s)
                .where(
                    eqRegion(region),
                    isAfterStartDate(startDate),
                    isBeforeEndDate(endDate)
                )
                .orderBy(s.id.desc())
                .fetch();
    }

    //region이 null이 아닌 경우 region 조건 추가
    private BooleanExpression eqRegion(String region) {
        if (region == null) {
            return null;
        }
        return service.hi.region.eq(region);
    }

    private BooleanExpression isAfterStartDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return null;
        }
        try {
            LocalDate startDateTime = LocalDate.parse(startDate, formatter);
            return service.sDate.goe(startDateTime.atStartOfDay());
        } catch (EXCEPTION e) {
            // 날짜 형식이 잘못된 경우 처리
            return null;
        }
    }

    private BooleanExpression isBeforeEndDate(String endDate) {
        if (endDate == null || endDate.isEmpty()) {
            return null;
        }
        try {
            LocalDate endDateTime = LocalDate.parse(endDate, formatter);
            return service.sDate.loe(endDateTime.atTime(23, 59, 59));
        } catch (EXCEPTION e) {
            // 날짜 형식이 잘못된 경우 처리
            return null;
        }
    }

}
