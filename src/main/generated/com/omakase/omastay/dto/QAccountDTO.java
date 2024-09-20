package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QAccountDTO is a Querydsl Projection type for AccountDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAccountDTO extends ConstructorExpression<AccountDTO> {

    private static final long serialVersionUID = -912506493L;

    public QAccountDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> acNum, com.querydsl.core.types.Expression<Integer> hIdx, com.querydsl.core.types.Expression<String> acBank, com.querydsl.core.types.Expression<String> acName, com.querydsl.core.types.Expression<String> acNone) {
        super(AccountDTO.class, new Class<?>[]{int.class, String.class, int.class, String.class, String.class, String.class}, id, acNum, hIdx, acBank, acName, acNone);
    }

}

