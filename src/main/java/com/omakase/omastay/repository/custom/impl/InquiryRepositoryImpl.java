package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.InquiryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class InquiryRepositoryImpl implements InquiryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public InquiryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
}
