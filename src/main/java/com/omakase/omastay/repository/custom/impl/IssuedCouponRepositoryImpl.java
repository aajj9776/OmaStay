package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.IssuedCouponRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class IssuedCouponRepositoryImpl implements IssuedCouponRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public IssuedCouponRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
