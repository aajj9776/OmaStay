package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.CalculationRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CalculationRepositoryImpl implements CalculationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CalculationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
