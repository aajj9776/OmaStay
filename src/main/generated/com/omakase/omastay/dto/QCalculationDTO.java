package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QCalculationDTO is a Querydsl Projection type for CalculationDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCalculationDTO extends ConstructorExpression<CalculationDTO> {

    private static final long serialVersionUID = -1698863385L;

    public QCalculationDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> hIdx, com.querydsl.core.types.Expression<Integer> calAmount, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> calStatus, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.StartEndVo> calStartEnd, com.querydsl.core.types.Expression<java.time.LocalDateTime> calLegTime, com.querydsl.core.types.Expression<java.time.LocalDateTime> calConfirmTime, com.querydsl.core.types.Expression<java.time.LocalDateTime> calCompleteTime, com.querydsl.core.types.Expression<java.time.LocalDateTime> calCancelTime, com.querydsl.core.types.Expression<String> calNone) {
        super(CalculationDTO.class, new Class<?>[]{int.class, int.class, int.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, com.omakase.omastay.vo.StartEndVo.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, String.class}, id, hIdx, calAmount, calStatus, calStartEnd, calLegTime, calConfirmTime, calCompleteTime, calCancelTime, calNone);
    }

}

