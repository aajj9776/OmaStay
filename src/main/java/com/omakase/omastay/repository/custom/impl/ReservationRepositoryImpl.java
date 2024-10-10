package com.omakase.omastay.repository.custom.impl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.omakase.omastay.entity.QHostInfo;
import com.omakase.omastay.entity.QPayment;
import com.omakase.omastay.entity.QReservation;
import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;



public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final QReservation reservation = QReservation.reservation;
    private static final QRoomInfo roomInfo = QRoomInfo.roomInfo;
    private static final QHostInfo hostInfo = QHostInfo.hostInfo;
    private static final QPayment payment = QPayment.payment;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ReservationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Reservation> searchRes(String resStatus, String startDate, String endDate, RoomInfo roomInfo) {

        if (startDate == null && endDate == null) {

            return queryFactory.selectFrom(reservation)
                    .where(
                        containsResStatus(resStatus),
                        reservation.roomInfo.eq(roomInfo)
                    )
                    .orderBy(reservation.id.desc())
                    .fetch();
        } else {
            LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
            LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).atTime(23, 59, 59);
            return queryFactory.selectFrom(reservation)
                .where(
                    containsResStatus(resStatus),
                    reservation.startEndVo.start.after(startDateTime),
                    reservation.startEndVo.end.before(endDateTime),
                    reservation.roomInfo.eq(roomInfo)
                )
                .orderBy(reservation.id.desc())
                .fetch();
        }        
    }

    private BooleanExpression containsResStatus(String resStatus) {
        if ("ALL".equalsIgnoreCase(resStatus)){
          	    return null;
        } else {       
		    return reservation.resStatus.eq(ResStatus.valueOf(resStatus));
        }
    }

    // 관리자의 회원 상세 조회에서 최근 예약 내역 5건 가져옴
    public List<Reservation> get5List(Integer memId) {
        return queryFactory
                .select(reservation)
                .from(reservation)
                .join(reservation.roomInfo, roomInfo).fetchJoin()
                .join(roomInfo.hostInfo, hostInfo).fetchJoin() 
                .join(reservation.payment, payment).fetchJoin() 
                .where(reservation.member.id.eq(memId))
                .orderBy(payment.payDate.desc())
                .limit(5) // 결과를 5건으로 제한
                .fetch();
    }

}