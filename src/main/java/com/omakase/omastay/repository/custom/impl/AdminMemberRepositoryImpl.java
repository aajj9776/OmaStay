package com.omakase.omastay.repository.custom.impl;



import com.omakase.omastay.repository.custom.AdminMemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class AdminMemberRepositoryImpl implements AdminMemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AdminMemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


}
