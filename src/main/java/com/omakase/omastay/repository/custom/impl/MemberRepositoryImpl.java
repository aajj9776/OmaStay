package com.omakase.omastay.repository.custom.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.custom.QAdminMainCustomDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.QMember;
import com.omakase.omastay.repository.custom.MemberRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    QMember member = QMember.member;

    public MemberRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Member> getMemList(){
        return queryFactory
                .select(member)
                .from(member)
                .orderBy(member.memJoinDate.desc())
                .limit(10)
                .fetch();
    }

}
