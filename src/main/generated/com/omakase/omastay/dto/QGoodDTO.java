package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QGoodDTO is a Querydsl Projection type for GoodDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGoodDTO extends ConstructorExpression<GoodDTO> {

    private static final long serialVersionUID = -622565263L;

    public QGoodDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> revIdx, com.querydsl.core.types.Expression<Integer> memIdx, com.querydsl.core.types.Expression<java.time.LocalDateTime> goodDate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> goodStatus, com.querydsl.core.types.Expression<String> goodNone) {
        super(GoodDTO.class, new Class<?>[]{int.class, int.class, int.class, java.time.LocalDateTime.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, String.class}, id, revIdx, memIdx, goodDate, goodStatus, goodNone);
    }

}

