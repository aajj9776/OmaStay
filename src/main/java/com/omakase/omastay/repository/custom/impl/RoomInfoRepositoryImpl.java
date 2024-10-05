package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.Service;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDateTime;
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

        // 조건 2 및 3: 예약 정보가 없는 경우, 예약 가능한 날짜인지 확인, 또는 예약이 취소된 경우 포함
        builder.and(
                reservation.isNull()
                        .or(startEndDayNotBetween(startEndDay)
                                .or(reservation.resStatus.eq(ResStatus.CANCELLED).and(
                                        startEndDayOverlaps(startEndDay)
                                ))
                        )
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

    // 예약이 겹치지 않는지 여부를 확인하는 메서드
    private BooleanExpression startEndDayNotBetween(StartEndVo startEndDay) {
        LocalDateTime start = startEndDay.getStart();
        LocalDateTime end = startEndDay.getEnd().minusDays(1).withHour(23).withMinute(59).withSecond(59);

        return reservation.startEndVo.start.goe(end).or(reservation.startEndVo.end.loe(start));
    }

    // 예약이 겹치는지 여부를 확인하는 메서드
    private BooleanExpression startEndDayOverlaps(StartEndVo startEndDay) {
        LocalDateTime start = startEndDay.getStart();
        LocalDateTime end = startEndDay.getEnd().minusDays(1).withHour(23).withMinute(59).withSecond(59); // 종료 날짜를 명확히 설정

        // 예약 시작 날짜가 시작 날짜 이전이고, 예약 종료 날짜가 종료 날짜 이후인 경우
        return reservation.startEndVo.start.loe(end).and(reservation.startEndVo.end.goe(start));
    }

    @Override
    public List<RoomInfo> searchRoom(String type, String keyword, HostInfo hostInfo) {
        return queryFactory.selectFrom(roomInfo)
                .where(
                    containsKeyword(type, keyword),
                    roomInfo.hostInfo.eq(hostInfo),
                    roomInfo.roomStatus.eq(BooleanStatus.TRUE)
                )
                .orderBy(roomInfo.id.desc())
                .fetch();
    }

    private BooleanExpression containsKeyword(String type, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        switch (type) {
            case "all":
                return roomInfo.roomName.contains(keyword)
                        .or(roomInfo.roomType.contains(keyword));
            case "roomType":
                return roomInfo.roomType.contains(keyword);
            case "roomName":
                return roomInfo.roomName.contains(keyword);
            default:
                return null;
        }
    }
}