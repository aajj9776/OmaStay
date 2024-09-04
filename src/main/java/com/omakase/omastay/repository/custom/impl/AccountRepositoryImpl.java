package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.dto.QAccountDTO;
import com.omakase.omastay.repository.custom.AccountRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;
import static com.omakase.omastay.entity.QAccount.account;


public class AccountRepositoryImpl implements AccountRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccountRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }



}
