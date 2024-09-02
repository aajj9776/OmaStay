package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.VisitorRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class VisitorRepositoryImpl implements VisitorRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public VisitorRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
