package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QReviewDTO is a Querydsl Projection type for ReviewDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewDTO extends ConstructorExpression<ReviewDTO> {

    private static final long serialVersionUID = -2142912458L;

    public QReviewDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> memberId, com.querydsl.core.types.Expression<Integer> reservationId, com.querydsl.core.types.Expression<Integer> hIdx, com.querydsl.core.types.Expression<String> revWriter, com.querydsl.core.types.Expression<String> revContent, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.FileImageNameVo> revfileImageNameVo, com.querydsl.core.types.Expression<java.time.LocalDateTime> revDate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> revStatus, com.querydsl.core.types.Expression<Float> revRating, com.querydsl.core.types.Expression<String> revNone) {
        super(ReviewDTO.class, new Class<?>[]{int.class, int.class, int.class, int.class, String.class, String.class, com.omakase.omastay.vo.FileImageNameVo.class, java.time.LocalDateTime.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, float.class, String.class}, id, memberId, reservationId, hIdx, revWriter, revContent, revfileImageNameVo, revDate, revStatus, revRating, revNone);
    }

}

