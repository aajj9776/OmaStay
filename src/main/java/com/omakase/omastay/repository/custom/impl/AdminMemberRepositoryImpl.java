package com.omakase.omastay.repository.custom.impl;



import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.QAdminMemberDTO;
import com.omakase.omastay.entity.QAdminMember;
import com.omakase.omastay.repository.custom.AdminMemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class AdminMemberRepositoryImpl implements AdminMemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AdminMemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


}
