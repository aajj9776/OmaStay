package com.omakase.omastay.repository.custom.impl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.omakase.omastay.entity.QReservation;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;



public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final QReservation reservation = QReservation.reservation;
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

}