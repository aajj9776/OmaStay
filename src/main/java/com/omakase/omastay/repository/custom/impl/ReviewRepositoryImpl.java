package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
