package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QMemberDTO is a Querydsl Projection type for MemberDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMemberDTO extends ConstructorExpression<MemberDTO> {

    private static final long serialVersionUID = -1293726284L;

    public QMemberDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> gIdx, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.UserProfileVo> memberProfile, com.querydsl.core.types.Expression<String> memPhone, com.querydsl.core.types.Expression<String> memName, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> memEmailCheck, com.querydsl.core.types.Expression<String> memBirth, com.querydsl.core.types.Expression<java.time.LocalDateTime> memJoinDate, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.Social> memSocial, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.AddressVo> addressVo, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.Gender> memGender, com.querydsl.core.types.Expression<String> accessToken, com.querydsl.core.types.Expression<String> refreshToken, com.querydsl.core.types.Expression<String> memNone) {
        super(MemberDTO.class, new Class<?>[]{int.class, int.class, com.omakase.omastay.vo.UserProfileVo.class, String.class, String.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, String.class, java.time.LocalDateTime.class, com.omakase.omastay.entity.enumurate.Social.class, com.omakase.omastay.vo.AddressVo.class, com.omakase.omastay.entity.enumurate.Gender.class, String.class, String.class, String.class}, id, gIdx, memberProfile, memPhone, memName, memEmailCheck, memBirth, memJoinDate, memSocial, addressVo, memGender, accessToken, refreshToken, memNone);
    }

}

