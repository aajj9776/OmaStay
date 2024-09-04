package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.PriceRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PriceRepositoryImpl implements PriceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PriceRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
