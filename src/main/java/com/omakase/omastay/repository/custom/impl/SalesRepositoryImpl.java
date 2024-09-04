package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.SalesRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class SalesRepositoryImpl implements SalesRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SalesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
