package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ReservationRepositoryImpl implements ReservationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ReservationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
