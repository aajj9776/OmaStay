package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.repository.custom.AccountRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;



public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccountRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }



}
