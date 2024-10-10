package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.QPrice;
import com.omakase.omastay.repository.custom.PriceRepositoryCustom;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

import static com.omakase.omastay.entity.QPrice.price;

public class PriceRepositoryImpl implements PriceRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PriceRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Price> findAvgPriceByHostIds(List<Integer> hostIds) {
        QPrice price2 = new QPrice("price2");

        // 조건 빌더 초기화
        BooleanBuilder builder = new BooleanBuilder();

        // 1. 각 호스트별 최저 가격 방 ID를 가져옴
        List<Integer> lowestPricesByHost = queryFactory
                .select(price.roomInfo.id)
                .from(price)
                .where(price.hostInfo.id.in(hostIds)
                        .and(price.regularPrice.eq(
                                JPAExpressions
                                        .select(price2.regularPrice.min())
                                        .from(price2)
                                        .where(price2.hostInfo.id.eq(price.hostInfo.id))
                                        .groupBy(price2.hostInfo.id)
                        ))
                )
                .fetch();

        System.out.println("lowestPricesByHost = " + lowestPricesByHost);

        // 2. 최저 가격의 방에 해당하는 price 객체를 가져옴
        return queryFactory
                .selectFrom(price)
                .where(price.roomInfo.id.in(lowestPricesByHost))
                .fetch();
    }
}
