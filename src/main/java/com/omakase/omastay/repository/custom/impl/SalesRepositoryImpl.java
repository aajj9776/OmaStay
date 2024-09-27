package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.QHostInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.dto.custom.QHostSalesDTO;
import com.omakase.omastay.entity.QPayment;
import com.omakase.omastay.entity.QReservation;
import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.entity.QSales;
import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import groovy.transform.Undefined.EXCEPTION;

public class SalesRepositoryImpl implements SalesRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public static final QSales sales = QSales.sales;
    public static final QReservation reservation = QReservation.reservation;
    public static final QRoomInfo roomInfo = QRoomInfo.roomInfo;
    public static final QPayment payment = QPayment.payment;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private QHostInfo hi = QHostInfo.hostInfo;


    public SalesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<Top5SalesDTO> findTop5SalesByRegion(String region){

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(sales.salDate.month().eq(LocalDate.now().getMonthValue()));
        builder.and(payment.payStatus.eq(PayStatus.PAY));

        if (region != null) {
            builder.and(hi.region.eq(region)); 
        }

        return queryFactory
                .select(Projections.constructor( // Top5SalesDTO 생성자 사용
                        Top5SalesDTO.class,
                        hi.hname.as("hostName"),
                        payment.nsalePrice.castToNum(Integer.class).sum().as("totalSales")
                ))
                .from(sales)
                .join(sales.hostInfo, hi)
                .join(sales.reservation, reservation)
                .join(reservation.roomInfo, roomInfo) // roomInfo 조인 추가
                .join(reservation.payment, payment)
                .where(builder)
                .groupBy(hi.hname)
                .orderBy(payment.nsalePrice.castToNum(Integer.class).sum().desc())
                .limit(5)
                .fetch();
    }

    //Sales 테이블 검색
    @Override
    public List<Sales> searchSales(String startDate, String endDate, String region) {

        return queryFactory.selectFrom(sales)
                .join(sales.hostInfo, hi)
                .join(sales.reservation, reservation)
                .join(reservation.payment, payment)
                .join(reservation.roomInfo, roomInfo)
                .where(
                    eqRegion(region),
                    isAfterStartDate(startDate),
                    isBeforeEndDate(endDate)
                )
                .orderBy(sales.id.desc())
                .fetch();
    }

    //region이 null이 아닌 경우 region 조건 추가
    private BooleanExpression eqRegion(String region) {
        if (region.equals("전체")) {
            return null;
        }
        return sales.hostInfo.region.contains(region);
    }

    private BooleanExpression isAfterStartDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return null;
        }
        try {
            LocalDate startDateTime = LocalDate.parse(startDate, formatter);
            return sales.reservation.startEndVo.start.goe(startDateTime.atStartOfDay());
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
            return sales.reservation.startEndVo.end.loe(endDateTime.atTime(23, 59, 59));
        } catch (EXCEPTION e) {
            // 날짜 형식이 잘못된 경우 처리
            return null;
        }
    }

    @Override
    public List<HostSalesDTO> findHostSales(Integer hidx) {
        return queryFactory
        .select(Projections.constructor(HostSalesDTO.class,roomInfo.roomName, roomInfo.roomType, reservation.startEndVo, reservation.resNum, payment.payMethod, payment.payDate, sales.salDate, payment.nsalePrice))
                .from(sales)
                .join(sales.reservation, reservation)
                .join(reservation.roomInfo, roomInfo)
                .join(reservation.payment, payment)
                .where(roomInfo.hostInfo.id.eq(hidx))
                .orderBy(sales.id.desc())
                .fetch();
    }
     
    

    @Override
    public List<HostSalesDTO> searchHostSales(String roomType, String startDate, String endDate, Integer hidx) {
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        if (startDate != null && endDate != null) {
        startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
        endDateTime = LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
        }

        return queryFactory
        .select(Projections.constructor(HostSalesDTO.class,roomInfo.roomName, roomInfo.roomType, reservation.startEndVo, reservation.resNum, payment.payMethod, payment.payDate, sales.salDate, payment.nsalePrice))
                .from(sales)
                .join(sales.reservation, reservation)
                .join(reservation.roomInfo, roomInfo)
                .join(reservation.payment, payment)
                .where(roomInfo.hostInfo.id.eq(hidx)
                        .and(roomType != null ? roomInfo.roomType.eq(roomType) : null)
                        .and(startDate != null ? reservation.startEndVo.start.after(startDateTime) : null)
                        .and(endDate != null ? reservation.startEndVo.end.before(endDateTime) : null))
                .orderBy(sales.id.desc())
                .fetch();
    }

}
