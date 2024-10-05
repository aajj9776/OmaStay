package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.dto.CalculationDTO;
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
}
