package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.entity.enumurate.HStatus;
import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.omakase.omastay.entity.QHostInfo.hostInfo;
import static com.omakase.omastay.entity.QRoomInfo.roomInfo;

public class HostInfoRepositoryImpl implements HostInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HostInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Integer> keywordFiltering(String keyword) {
        BooleanBuilder builder = new BooleanBuilder();

        //조건1 h_status가 1(승인)인지
        // 조건1: h_status가 APPROVE인지 확인
        builder.and(hostInfo.hStatus.eq(HStatus.APPROVE));
        //조건2 h_name에 keyword가 포함되어 있는지
        builder.or(hostNameContains(keyword)); //업체명
        //조건3 h_post_code에 keyword가 포함되어 있는지
        builder.or(postCodeContains(keyword)); //우편번호
        //조건4 h_street 에 keyword가 포함되어 있는지
        builder.or(streetContains(keyword)); //주소
        //조건5 region에 keyword가 포함되어 있는지
        builder.or(regionContains(keyword)); //지역

        // 조건2: h_name, h_post_code, h_street, region 중 하나라도 keyword가 포함되어 있는지 확인
        BooleanBuilder orBuilder = new BooleanBuilder();
        // 조건 2-1: h_name이 keyword를 포함하는지 확인
        orBuilder.or(hostNameContains(keyword));
        // 조건 2-2: h_post_code가 keyword를 포함하는지 확인
        orBuilder.or(postCodeContains(keyword));
        // 조건 2-3: h_street이 keyword를 포함하는지 확인
        orBuilder.or(streetContains(keyword));
        // 조건 2-4: region이 keyword를 포함하는지 확인
        orBuilder.or(regionContains(keyword));

        // 조건 결합: h_status가 APPROVE이고 (h_name, h_post_code, h_street, region 중 하나가 keyword를 포함)
        builder.and(orBuilder);

        return queryFactory
                .select(roomInfo.id)  // roomInfo의 id만 선택
                .from(roomInfo)  // roomInfo 테이블로부터 시작
                .join(roomInfo.hostInfo, hostInfo)  // roomInfo와 hostInfo를 일반 조인
                .where(builder)  // 조건 설정
                .fetch();  // 결과 fetch
    }

    private BooleanExpression hostNameContains(String keyword) {
        return hostInfo.hname.containsIgnoreCase(keyword);
    }

    private BooleanExpression postCodeContains(String keyword) {
        return hostInfo.hostAddress.postCode.containsIgnoreCase(keyword);
    }

    private BooleanExpression streetContains(String keyword) {
        return hostInfo.hostAddress.street.containsIgnoreCase(keyword);
    }

    private BooleanExpression regionContains(String keyword) {
        return hostInfo.region.containsIgnoreCase(keyword);
    }

}
