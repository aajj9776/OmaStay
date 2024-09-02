package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class RecommendationRepositoryImpl implements RecommendationRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public RecommendationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
