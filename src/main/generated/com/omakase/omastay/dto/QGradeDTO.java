package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QGradeDTO is a Querydsl Projection type for GradeDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGradeDTO extends ConstructorExpression<GradeDTO> {

    private static final long serialVersionUID = 143016921L;

    public QGradeDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> gCate, com.querydsl.core.types.Expression<String> gReq, com.querydsl.core.types.Expression<String> gSale, com.querydsl.core.types.Expression<String> gPoint, com.querydsl.core.types.Expression<String> gNone) {
        super(GradeDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class}, id, gCate, gReq, gSale, gPoint, gNone);
    }

}

