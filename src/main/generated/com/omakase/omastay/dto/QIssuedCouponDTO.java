package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QIssuedCouponDTO is a Querydsl Projection type for IssuedCouponDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QIssuedCouponDTO extends ConstructorExpression<IssuedCouponDTO> {

    private static final long serialVersionUID = -295834883L;

    public QIssuedCouponDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> mIdx, com.querydsl.core.types.Expression<Integer> cpIdx, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.IcStatus> icStatus, com.querydsl.core.types.Expression<String> icCode, com.querydsl.core.types.Expression<String> icNone) {
        super(IssuedCouponDTO.class, new Class<?>[]{int.class, int.class, int.class, com.omakase.omastay.entity.enumurate.IcStatus.class, String.class, String.class}, id, mIdx, cpIdx, icStatus, icCode, icNone);
    }

}

