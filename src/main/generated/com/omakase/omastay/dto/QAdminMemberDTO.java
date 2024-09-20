package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QAdminMemberDTO is a Querydsl Projection type for AdminMemberDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QAdminMemberDTO extends ConstructorExpression<AdminMemberDTO> {

    private static final long serialVersionUID = -1509342489L;

    public QAdminMemberDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> adId, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.UserProfileVo> adminProfile, com.querydsl.core.types.Expression<Integer> adAuth, com.querydsl.core.types.Expression<String> adNone) {
        super(AdminMemberDTO.class, new Class<?>[]{int.class, String.class, com.omakase.omastay.vo.UserProfileVo.class, int.class, String.class}, id, adId, adminProfile, adAuth, adNone);
    }

}

