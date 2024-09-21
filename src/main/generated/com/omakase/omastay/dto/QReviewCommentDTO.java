package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QReviewCommentDTO is a Querydsl Projection type for ReviewCommentDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReviewCommentDTO extends ConstructorExpression<ReviewCommentDTO> {

    private static final long serialVersionUID = 2000145609L;

    public QReviewCommentDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> revIdx, com.querydsl.core.types.Expression<String> rcComment, com.querydsl.core.types.Expression<java.time.LocalDateTime> rcDate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> rcStatus, com.querydsl.core.types.Expression<String> rcNone) {
        super(ReviewCommentDTO.class, new Class<?>[]{int.class, int.class, String.class, java.time.LocalDateTime.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, String.class}, id, revIdx, rcComment, rcDate, rcStatus, rcNone);
    }

}

