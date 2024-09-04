package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.PointRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PointRepositoryImpl implements PointRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PointRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

}
