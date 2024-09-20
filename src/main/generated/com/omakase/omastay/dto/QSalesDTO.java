package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QSalesDTO is a Querydsl Projection type for SalesDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSalesDTO extends ConstructorExpression<SalesDTO> {

    private static final long serialVersionUID = 1990472324L;

    public QSalesDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> hostInfoId, com.querydsl.core.types.Expression<Integer> reservationId, com.querydsl.core.types.Expression<java.time.LocalDate> salPeriod, com.querydsl.core.types.Expression<java.time.LocalDate> salDate, com.querydsl.core.types.Expression<String> salNone) {
        super(SalesDTO.class, new Class<?>[]{int.class, int.class, int.class, java.time.LocalDate.class, java.time.LocalDate.class, String.class}, id, hostInfoId, reservationId, salPeriod, salDate, salNone);
    }

}

