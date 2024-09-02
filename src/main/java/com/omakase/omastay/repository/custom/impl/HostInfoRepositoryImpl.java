package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class HostInfoRepositoryImpl implements HostInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HostInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

}
