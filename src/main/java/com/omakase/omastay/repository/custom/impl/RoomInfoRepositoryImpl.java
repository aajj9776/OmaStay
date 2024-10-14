package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.entity.*;
import com.omakase.omastay.entity.QHostFacilities;
import com.omakase.omastay.entity.QHostInfo;
import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.lettuce.core.output.SocketAddressOutput;

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
        System.out.println(startEndDay);

        BooleanBuilder builder = new BooleanBuilder();

        // 조건 1: roomInfos 목록에 있는 RoomInfo 아이디와 일치하는 RoomInfo
        builder.and(roomInfo.id.in(roomInfos));

        // 조건 2: 주어진 날짜 범위에 예약이 없는 경우를 찾는 조건
        BooleanBuilder noOverlapCondition = new BooleanBuilder();
        noOverlapCondition.and(
                JPAExpressions.selectOne()
                        .from(reservation)
                        .where(
                                reservation.roomInfo.id.eq(roomInfo.id)
                                        .and(reservation.startEndVo.start.lt(startEndDay.getEnd()))
                                                .and(reservation.startEndVo.end.gt(startEndDay.getStart()))
                        ).notExists()
        );

        // 최종 조건: 지정된 날짜에 예약이 없는 방을 찾는 조건
        builder.and(noOverlapCondition);

        // 쿼리 실행
        return queryFactory
                .select(hostInfo.id, roomInfo.id)
                .from(roomInfo)
                .leftJoin(roomInfo.hostInfo, hostInfo)
                .leftJoin(reservation).on(roomInfo.eq(reservation.roomInfo))
                .where(builder)
                .distinct()
                .fetch();
    }

    @Override
    public List<Tuple> personFiltering(FilterDTO filterDTO, List<Integer> roomInfos) {
        QRoomInfo roomInfo = com.omakase.omastay.entity.QRoomInfo.roomInfo;
        QHostInfo hostInfo = QHostInfo.hostInfo;
        QHostFacilities hostFacilities = QHostFacilities.hostFacilities;

        BooleanBuilder builder = new BooleanBuilder();

        Integer person = filterDTO.getPerson();

        // 조건 추가
        builder.and(roomStatusCondition(roomInfo));
        builder.and(roomInfosCondition(roomInfo, roomInfos));
        builder.and(personCondition(roomInfo, person));

        return queryFactory
                .select(roomInfo.id, hostInfo.id)
                .from(roomInfo)
                .join(roomInfo.hostInfo, hostInfo)
                .where(builder).distinct()
                .fetch();
    }


    @Override
    public List<Tuple> findHostsByRoomIds(List<Integer> roomIds) {
        return (queryFactory
                .select(hostInfo.id, hostInfo.hname, hostInfo.hCate, hostInfo.xAxis, hostInfo.yAxis)  // hname 변경
                .from(roomInfo)
                .join(hostInfo).on(roomInfo.hostInfo.id.eq(hostInfo.id))
                .where(roomInfo.id.in(roomIds)).distinct()
                .fetch());
    }

    // 예약이 겹치지 않는지 여부를 확인하는 메서드
    private BooleanExpression startEndDayNotBetween(StartEndVo startEndDay) {
        LocalDateTime start = startEndDay.getStart(); // 주어진 기간의 시작 날짜
        LocalDateTime end = startEndDay.getEnd() // 주어진 기간의 끝 날짜를 하루 전으로 설정하고 시간을 설정
                .minusDays(1).withHour(23).withMinute(59).withSecond(59);

        System.out.println("start: " + start);
        System.out.println("end: " + end);
        // 예약의 시작 날짜가 주어진 종료 날짜 이후거나, 예약의 종료 날짜가 주어진 시작 날짜 이전일 경우
        return reservation.startEndVo.start.goe(start)
                .or(reservation.startEndVo.end.loe(end));
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

    private BooleanExpression roomStatusCondition(QRoomInfo roomInfo) {
        return roomInfo.roomStatus.eq(BooleanStatus.TRUE);
    }

    private BooleanExpression roomInfosCondition(QRoomInfo roomInfo, List<Integer> roomInfos) {
        return roomInfo.id.in(roomInfos);
    }

    private BooleanExpression personCondition(QRoomInfo roomInfo, Integer person) {
        return roomInfo.roomPerson.goe(person);
    }
}