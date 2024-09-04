package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.PaymentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PaymentRepositoryImpl implements PaymentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PaymentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
