package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QFacilitiesDTO is a Querydsl Projection type for FacilitiesDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QFacilitiesDTO extends ConstructorExpression<FacilitiesDTO> {

    private static final long serialVersionUID = -2139793171L;

    public QFacilitiesDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> fCate, com.querydsl.core.types.Expression<String> fNone) {
        super(FacilitiesDTO.class, new Class<?>[]{int.class, String.class, String.class}, id, fCate, fNone);
    }

}

