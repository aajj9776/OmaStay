package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QInquiryDTO is a Querydsl Projection type for InquiryDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QInquiryDTO extends ConstructorExpression<InquiryDTO> {

    private static final long serialVersionUID = -1571888215L;

    public QInquiryDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> mIdx, com.querydsl.core.types.Expression<String> iqTitle, com.querydsl.core.types.Expression<String> iqContent, com.querydsl.core.types.Expression<String> iqWriter, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.FileImageNameVo> fileName, com.querydsl.core.types.Expression<java.time.LocalDateTime> iqDate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> iqStatus, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.CStatus> cStatus, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.UserAuth> iqAuth, com.querydsl.core.types.Expression<String> iqNone) {
        super(InquiryDTO.class, new Class<?>[]{int.class, int.class, String.class, String.class, String.class, com.omakase.omastay.vo.FileImageNameVo.class, java.time.LocalDateTime.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, com.omakase.omastay.entity.enumurate.CStatus.class, com.omakase.omastay.entity.enumurate.UserAuth.class, String.class}, id, mIdx, iqTitle, iqContent, iqWriter, fileName, iqDate, iqStatus, cStatus, iqAuth, iqNone);
    }

}

