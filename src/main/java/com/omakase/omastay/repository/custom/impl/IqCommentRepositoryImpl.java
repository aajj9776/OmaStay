package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.IqCommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class IqCommentRepositoryImpl implements IqCommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public IqCommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
