package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.ReviewCommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ReviewCommentRepositoryImpl implements ReviewCommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ReviewCommentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
