package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.ServiceRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ServiceRepositoryImpl implements ServiceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ServiceRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
