package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.omakase.omastay.entity.QHostInfo.hostInfo;
import static com.omakase.omastay.entity.QReservation.reservation;
import static com.omakase.omastay.entity.QRoomInfo.roomInfo;

public class RoomInfoRepositoryImpl implements RoomInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RoomInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Set<Integer> dateFiltering(StartEndVo startEndDay, List<Integer> hIdxs) {
        BooleanBuilder builder = new BooleanBuilder();

        // 1번 조건: hIdxs의 값이 roomInfo의 hostInfo.id의 값과 같은지
        builder.and(roomInfo.hostInfo.id.in(hIdxs));

        // 2번 조건: 예약이 가능한 날짜인지
        builder.and(startEndDayNotBetween(startEndDay));

        // 3번 조건: res_status가 0,1이 아닌 경우 (예약 상태를 확인하기 위해 조인)
        builder.and(
                reservation.resStatus.ne(ResStatus.PENDING)
                        .and(reservation.resStatus.ne(ResStatus.COMPLETED))
                        .or(reservation.isNull())
        );


        return new HashSet<>(queryFactory
                .select(roomInfo.id)
                .from(roomInfo)
                .leftJoin(reservation).on(reservation.roomInfo.id.eq(roomInfo.id))
                .where(builder)
                .fetch());
    }

    public Set<HostInfo> personFiltering(int person, Set<Integer> hostInfos) {
        BooleanBuilder builder = new BooleanBuilder();

        // 1. room_status true인 것
        //builder.and(roomInfo.roomStatus.eq(BooleanStatus.TRUE));

        // 2. hostInfo의 id가 hostInfos에 포함되어 있는 것
        builder.and(roomInfo.hostInfo.id.in(hostInfos));

        // 3. room_person이 person보다 크거나 같은 것
        builder.and(roomInfo.roomPerson.goe(person));

        // 조인 조건을 조금 더 명확히 하기 위해 id 필드 비교를 추가할 수 있음
        return new HashSet<>(queryFactory
                .select(roomInfo.hostInfo)
                .from(roomInfo)
                .join(roomInfo.hostInfo, hostInfo)
                .where(builder)
                .fetch());
    }

    private BooleanExpression startEndDayNotBetween(StartEndVo startEndDay) {
        LocalDateTime start = startEndDay.getStart();
        LocalDateTime end = startEndDay.getEnd();

        return reservation.startEndVo.start.goe(end).or(reservation.startEndVo.end.loe(start));
    }


}
