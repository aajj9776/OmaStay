package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QPointDTO is a Querydsl Projection type for PointDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPointDTO extends ConstructorExpression<PointDTO> {

    private static final long serialVersionUID = 519612288L;

    public QPointDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> memIdx, com.querydsl.core.types.Expression<Integer> pSum, com.querydsl.core.types.Expression<Integer> pValue, com.querydsl.core.types.Expression<java.time.LocalDateTime> pDate) {
        super(PointDTO.class, new Class<?>[]{int.class, int.class, int.class, int.class, java.time.LocalDateTime.class}, id, memIdx, pSum, pValue, pDate);
    }

}

