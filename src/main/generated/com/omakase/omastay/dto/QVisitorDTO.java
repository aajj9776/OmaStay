package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QVisitorDTO is a Querydsl Projection type for VisitorDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QVisitorDTO extends ConstructorExpression<VisitorDTO> {

    private static final long serialVersionUID = -1941773406L;

    public QVisitorDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> vIp, com.querydsl.core.types.Expression<String> vRefer, com.querydsl.core.types.Expression<String> vAgent, com.querydsl.core.types.Expression<String> vNone, com.querydsl.core.types.Expression<java.time.LocalDateTime> vDate) {
        super(VisitorDTO.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, vIp, vRefer, vAgent, vNone, vDate);
    }

}

