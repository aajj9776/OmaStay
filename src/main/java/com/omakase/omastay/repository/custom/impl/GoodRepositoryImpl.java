package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.GoodRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class GoodRepositoryImpl implements GoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public GoodRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

}
