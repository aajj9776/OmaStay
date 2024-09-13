package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.HostFacilitiesRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class HostFacilitiesRepositoryImpl implements HostFacilitiesRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public HostFacilitiesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
