package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.HostFacilitiesRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.omakase.omastay.entity.QHostFacilities.hostFacilities;

public class HostFacilitiesRepositoryImpl implements HostFacilitiesRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public HostFacilitiesRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Integer> findFacilitiesIdsByHostId(List<Integer> facilities, List<Integer> hostIds) {
        System.out.println(facilities.size() + " 사이즈");
        System.out.println(hostIds.size() + " 호스트ID 사이즈");

        return queryFactory.select(hostFacilities.hostInfo.id)
                .from(hostFacilities)
                .where(
                        hostFacilities.hostInfo.id.in(hostIds)
                                .and(hostFacilities.facilities.id.in(facilities))
                )
                .groupBy(hostFacilities.hostInfo.id)
                .having(hostFacilities.facilities.id.count().eq((long) facilities.size()))
                .fetch();
    }
}
