package com.omakase.omastay.repository.custom.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.omakase.omastay.entity.QService;
import com.omakase.omastay.entity.Service;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.repository.custom.ServiceRepositoryCustom;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import groovy.transform.Undefined.EXCEPTION;

public class ServiceRepositoryImpl implements ServiceRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final QService service = QService.service; 
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public ServiceRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Service> searchHostNotice(String type, String keyword, String startDate, String endDate) {
        return queryFactory.selectFrom(service)
                .where(
                    containsKeyword(keyword),
                    isAfterStartDate(startDate),
                    isBeforeEndDate(endDate),
                    service.sAuth.eq(UserAuth.HOST),
                    service.sCate.eq(SCate.NOTICE),
                    service.sStatus.eq(BooleanStatus.TRUE)
                )
                .orderBy(service.id.desc())
                .fetch();
    }

    private BooleanExpression containsKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return service.sTitle.contains(keyword).or(service.sContent.contains(keyword));
    }

    private BooleanExpression isAfterStartDate(String startDate) {
        if (startDate == null || startDate.isEmpty()) {
            return null;
        }
        try {
            LocalDate startDateTime = LocalDate.parse(startDate, formatter);
            return service.sDate.goe(startDateTime.atStartOfDay());
        } catch (EXCEPTION e) {
            // 날짜 형식이 잘못된 경우 처리
            return null;
        }
    }

    private BooleanExpression isBeforeEndDate(String endDate) {
        if (endDate == null || endDate.isEmpty()) {
            return null;
        }
        try {
            LocalDate endDateTime = LocalDate.parse(endDate, formatter);
            return service.sDate.loe(endDateTime.atTime(23, 59, 59));
        } catch (EXCEPTION e) {
            // 날짜 형식이 잘못된 경우 처리
            return null;
        }
    }
}
