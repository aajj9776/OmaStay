package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.RoomFacilitiesRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RoomFacilitiesRepositoryImpl implements RoomFacilitiesRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RoomFacilitiesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
