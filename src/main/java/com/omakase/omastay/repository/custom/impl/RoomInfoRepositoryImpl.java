package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static com.omakase.omastay.entity.QHostInfo.hostInfo;
import static com.omakase.omastay.entity.QReservation.reservation;
import static com.omakase.omastay.entity.QRoomInfo.roomInfo;

public class RoomInfoRepositoryImpl implements RoomInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RoomInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> dateFiltering(StartEndVo startEndDay, List<Integer> roomInfos) {
        BooleanBuilder builder = new BooleanBuilder();

        // 조건 1: roomInfos 목록에 있는 RoomInfo 아이디와 일치하는 RoomInfo
        builder.and(roomInfo.id.in(roomInfos));

        // 조건 2: 예약이 가능한 날짜인지 확인
        builder.and(startEndDayNotBetween(startEndDay).or(reservation.isNull()));

        // 조건 3: 예약 상태를 확인 (PENDING 또는 CONFIRMED이 아닌 경우, 또는 예약 정보가 없는 경우)
        builder.and(
                reservation.isNull()
                        .or(startEndDayNotBetween(startEndDay)
                                .and(reservation.resStatus.ne(ResStatus.PENDING))
                                .and(reservation.resStatus.ne(ResStatus.CONFIRMED)))
        );

        return queryFactory
                .select(roomInfo.id, hostInfo.id)
                .from(roomInfo)
                .join(roomInfo.hostInfo, hostInfo)
                .leftJoin(reservation).on(roomInfo.eq(reservation.roomInfo))
                .where(builder).distinct()
                .fetch();
    }

    @Override
    public List<Tuple> personFiltering(int person, List<Integer> roomInfos) {
        BooleanBuilder builder = new BooleanBuilder();

        // 1. room_status true인 것
        builder.and(roomInfo.roomStatus.eq(BooleanStatus.TRUE));

        // 2. hostInfo의 id가 hostInfos에 포함되어 있는 것
        builder.and(roomInfo.id.in(roomInfos));

        // 3. room_person이 person보다 크거나 같은 것
        builder.and(roomInfo.roomPerson.goe(person));

        return queryFactory
                .select(roomInfo.id, hostInfo.id)
                .from(roomInfo).
                join(roomInfo.hostInfo, hostInfo)
                .where(builder).distinct()
                .fetch();
    }

    @Override
    public List<Tuple> findHostsByRoomIds(List<Integer> roomIdxs) {
        return (queryFactory
                .select(hostInfo.id, hostInfo.hname, hostInfo.hCate, hostInfo.xAxis, hostInfo.yAxis)  // hname 변경
                .from(roomInfo)
                .join(hostInfo).on(roomInfo.hostInfo.id.eq(hostInfo.id))
                .where(roomInfo.id.in(roomIdxs)).distinct()
                .fetch());
    }

    private BooleanExpression startEndDayNotBetween(StartEndVo startEndDay) {
        LocalDateTime start = startEndDay.getStart();
        LocalDateTime end = startEndDay.getEnd().minusDays(1).withHour(23).withMinute(59).withSecond(59);

        return reservation.startEndVo.start.goe(end).or(reservation.startEndVo.end.loe(start));
    }
}