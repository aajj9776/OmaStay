package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QCouponDTO is a Querydsl Projection type for CouponDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCouponDTO extends ConstructorExpression<CouponDTO> {

    private static final long serialVersionUID = -1230797656L;

    public QCouponDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> cpContent, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.StartEndVo> cpStartEnd, com.querydsl.core.types.Expression<String> cpSale, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.CpCate> cpCate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.CpMethod> cpMethod, com.querydsl.core.types.Expression<String> cpNone) {
        super(CouponDTO.class, new Class<?>[]{int.class, String.class, com.omakase.omastay.vo.StartEndVo.class, String.class, com.omakase.omastay.entity.enumurate.CpCate.class, com.omakase.omastay.entity.enumurate.CpMethod.class, String.class}, id, cpContent, cpStartEnd, cpSale, cpCate, cpMethod, cpNone);
    }

}

