package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QIqCommentDTO is a Querydsl Projection type for IqCommentDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QIqCommentDTO extends ConstructorExpression<IqCommentDTO> {

    private static final long serialVersionUID = 558411545L;

    public QIqCommentDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> iqIdx, com.querydsl.core.types.Expression<String> iqcTitle, com.querydsl.core.types.Expression<String> iqcContent, com.querydsl.core.types.Expression<java.time.LocalDateTime> iqcDate, com.querydsl.core.types.Expression<String> iqcNone) {
        super(IqCommentDTO.class, new Class<?>[]{int.class, int.class, String.class, String.class, java.time.LocalDateTime.class, String.class}, id, iqIdx, iqcTitle, iqcContent, iqcDate, iqcNone);
    }

}

