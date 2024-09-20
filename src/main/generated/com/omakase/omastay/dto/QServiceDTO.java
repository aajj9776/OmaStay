package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QServiceDTO is a Querydsl Projection type for ServiceDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QServiceDTO extends ConstructorExpression<ServiceDTO> {

    private static final long serialVersionUID = -1833936901L;

    public QServiceDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.SCate> sCate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.UserAuth> sAuth, com.querydsl.core.types.Expression<String> sTitle, com.querydsl.core.types.Expression<String> sContent, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.FileImageNameVo> fileName, com.querydsl.core.types.Expression<java.time.LocalDateTime> sDate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> sStatus, com.querydsl.core.types.Expression<String> sNone) {
        super(ServiceDTO.class, new Class<?>[]{int.class, com.omakase.omastay.entity.enumurate.SCate.class, com.omakase.omastay.entity.enumurate.UserAuth.class, String.class, String.class, com.omakase.omastay.vo.FileImageNameVo.class, java.time.LocalDateTime.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, String.class}, id, sCate, sAuth, sTitle, sContent, fileName, sDate, sStatus, sNone);
    }

}

