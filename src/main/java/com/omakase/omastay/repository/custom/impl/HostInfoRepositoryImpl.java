package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class HostInfoRepositoryImpl implements HostInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HostInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<HostInfo> keywordFiltering(String keyword) {
        return List.of();
    }
}
