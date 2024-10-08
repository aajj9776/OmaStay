package com.omakase.omastay.repository.custom.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omakase.omastay.dto.CalculationDTO;
import com.omakase.omastay.dto.custom.AdminMainCustomDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.dto.custom.QAdminMainCustomDTO;
import com.omakase.omastay.entity.QCalculation;
import com.omakase.omastay.repository.custom.CalculationRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class CalculationRepositoryImpl implements CalculationRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QCalculation calculation = QCalculation.calculation;

    public CalculationRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public CalculationDTO findHostAndMonth(Integer hidx, Integer calMonth) {
        return queryFactory
        .select(Projections.constructor(CalculationDTO.class,
            calculation.id,
            calculation.hostInfo.id,
            calculation.calAmount,
            calculation.calStatus,
            calculation.calMonth,
            calculation.calRegTime,
            calculation.calConfirmTime,
            calculation.calCompleteTime,
            calculation.calNone
        ))
        .from(calculation)
        .where(calculation.hostInfo.id.eq(hidx)
            .and(calculation.calMonth.month().eq(calMonth)))
        .fetchOne();
    }

    //Admin main에 사용되는 이번달 정산 count 쿼리
    public List<AdminMainCustomDTO> getCalculationCount(LocalDateTime thisMonth) {
        return queryFactory
                .select(new QAdminMainCustomDTO(
                        calculation.calStatus.stringValue(), // calStatus를 문자열로 변환
                        calculation.count()))
                .from(calculation)
                .where(calculation.calRegTime.goe(thisMonth)) // calRegTime 필터링
                .groupBy(calculation.calStatus) // calStatus로 그룹화
                .fetch();
    }
}
