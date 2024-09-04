package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.NonMemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class NonMemberRepositoryImpl implements NonMemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NonMemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
