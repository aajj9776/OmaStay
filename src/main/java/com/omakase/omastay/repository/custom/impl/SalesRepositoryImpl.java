package com.omakase.omastay.repository.custom.impl;


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
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SalesRepositoryImpl implements SalesRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public static final QSales sales = QSales.sales;
    public static final QReservation reservation = QReservation.reservation;
    public static final QRoomInfo roomInfo = QRoomInfo.roomInfo;
    public static final QPayment payment = QPayment.payment;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SalesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
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

    @Override
    public List<HostSalesDTO> findHostYearSales(Integer hidx, Integer year) {

        return queryFactory
        .select(Projections.constructor(HostSalesDTO.class,roomInfo.roomName, roomInfo.roomType, reservation.startEndVo, reservation.resNum, payment.payMethod, payment.payDate, sales.salDate, payment.nsalePrice))
                .from(sales)
                .join(sales.reservation, reservation)
                .join(reservation.roomInfo, roomInfo)
                .join(reservation.payment, payment)
                .where(roomInfo.hostInfo.id.eq(hidx)
                        .and(year != null ? payment.payDate.year().eq(year) : null))
                .orderBy(sales.id.desc())
                .fetch();
    }

    @Override
    public List<HostSalesDTO> findHostMonthSales(Integer hidx, Integer year, Integer month) {

        return queryFactory
        .select(Projections.constructor(HostSalesDTO.class,roomInfo.roomName, roomInfo.roomType, reservation.startEndVo, reservation.resNum, payment.payMethod, payment.payDate, sales.salDate, payment.nsalePrice))
                .from(sales)
                .join(sales.reservation, reservation)
                .join(reservation.roomInfo, roomInfo)
                .join(reservation.payment, payment)
                .where(roomInfo.hostInfo.id.eq(hidx)
                        .and(year != null ? payment.payDate.year().eq(year) : null)
                        .and(month != null ? payment.payDate.month().eq(month) : null))
                .orderBy(sales.id.desc())
                .fetch();
    }


}
