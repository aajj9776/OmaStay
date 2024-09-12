package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.entity.QRoomInfo;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.HStatus;
import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.omakase.omastay.entity.QHostInfo.hostInfo;
import static com.omakase.omastay.entity.QRoomInfo.roomInfo;

public class HostInfoRepositoryImpl implements HostInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HostInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Integer> keywordFiltering(String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        //조건1 h_status가 1인지
        builder.and(hostInfo.hStatus.eq(HStatus.APPROVE));
        //조건2 h_name에 keyword가 포함되어 있는지
        builder.or(hostNameContains(keyword));
        //조건3 h_post_code에 keyword가 포함되어 있는지
        builder.or(postCodeContains(keyword));
        //조건4 h_street 에 keyword가 포함되어 있는지
        builder.or(streetContains(keyword));
        //조건5 region에 keyword가 포함되어 있는지
        builder.or(regionContains(keyword));

        return queryFactory
                .select(roomInfo.id)
                .from(hostInfo).join(roomInfo).on(hostInfo.id.eq(roomInfo.hostInfo.id))
                .where(builder)
                .fetch();
    }

    private BooleanExpression hostNameContains (String keyword) {
        return keyword != null ? hostInfo.hname.contains(keyword) : null;
    }

    private BooleanExpression postCodeContains(String keyword) {
        return keyword != null ? hostInfo.hostAddress.postCode.contains(keyword) : null;
    }

    private BooleanExpression streetContains(String keyword) {
        return keyword != null ? hostInfo.hostAddress.street.contains(keyword) : null;
    }

    private BooleanExpression regionContains(String keyword) {
        return keyword != null ? hostInfo.region.contains(keyword) : null;
    }

}
