package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.FacilitiesRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class FacilitiesRepositoryImpl implements FacilitiesRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public FacilitiesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
