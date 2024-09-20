package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QNonMemberDTO is a Querydsl Projection type for NonMemberDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QNonMemberDTO extends ConstructorExpression<NonMemberDTO> {

    private static final long serialVersionUID = 814684521L;

    public QNonMemberDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> nonName, com.querydsl.core.types.Expression<String> nonPhone, com.querydsl.core.types.Expression<String> nonEmail, com.querydsl.core.types.Expression<String> nonNone) {
        super(NonMemberDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class}, id, nonName, nonPhone, nonEmail, nonNone);
    }

}

