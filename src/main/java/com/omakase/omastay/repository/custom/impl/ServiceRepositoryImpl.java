package com.omakase.omastay.repository.custom.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.omakase.omastay.entity.QService;
import com.omakase.omastay.entity.Sales;
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

    //service 엔티티 전체 가져오기
    @Override
    public List<Service> findBySCateAndSAuth(SCate sCate, UserAuth sAuth, BooleanStatus sStatus) {
        return queryFactory.selectFrom(service)
                .where(
                    eqSCate(sCate),
                    service.sAuth.eq(sAuth),
                    service.sStatus.eq(sStatus)
                )
                .orderBy(service.id.desc())
                .fetch();
    }

    //service 엔티티 검색
    @Override
    public List<Sales> searchSales(String startDate, String endDate, String region) {
        return queryFactory.selectFrom(service)
                .where(
                    containsKeyword(type, keyword),
                    isAfterStartDate(startDate),
                    isBeforeEndDate(endDate),
                    service.sAuth.eq(sAuth),
                    eqSCate(sCate),
                    service.sStatus.eq(BooleanStatus.TRUE)
                )
                .orderBy(service.id.desc())
                .fetch();
    }

    private BooleanExpression containsKeyword(String type, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        switch (type) {
            case "title":
                return service.sTitle.contains(keyword);
            case "ffname":
                return service.fileName.fName.contains(keyword);
            case "all":
                return service.sTitle.contains(keyword)
                        .or(service.fileName.fName.contains(keyword))
                        .or(service.sContent.contains(keyword));
            default:
                return null;
        }
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

    private BooleanExpression eqSCate(SCate sCate) {
        if (sCate == null) {
            return null;
        }
        return service.sCate.eq(sCate);
    }

    //service 엔티티 검색
    @Override
    public List<Service> searchHostNotice(String type, String keyword, UserAuth sAuth, SCate sCate) {
        return queryFactory.selectFrom(service)
                .where(
                    containsKeyword2(type, keyword),
                    service.sAuth.eq(sAuth),
                    eqSCate(sCate),
                    service.sStatus.eq(BooleanStatus.TRUE)
                )
                .orderBy(service.id.desc())
                .fetch();
    }

    private BooleanExpression containsKeyword2(String type, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        switch (type) {
            case "all":
                return service.sTitle.contains(keyword)
                        .or(service.sContent.contains(keyword));
            case "sTitle":
                return service.sTitle.contains(keyword);
            case "sContent":
                return service.sContent.contains(keyword);
            default:
                return null;
        }
    }
}
