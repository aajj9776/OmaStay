package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QHostInfoDTO is a Querydsl Projection type for HostInfoDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QHostInfoDTO extends ConstructorExpression<HostInfoDTO> {

    private static final long serialVersionUID = 587647128L;

    public QHostInfoDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> adIdx, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.AddressVo> addressVo, com.querydsl.core.types.Expression<String> region, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.HCate> hCate, com.querydsl.core.types.Expression<String> xAxis, com.querydsl.core.types.Expression<String> yAxis, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.HostOwnerInfoVo> hostOwnerInfo, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.HostContactInfoVo> hostContactInfo, com.querydsl.core.types.Expression<String> hurl, com.querydsl.core.types.Expression<String> checkin, com.querydsl.core.types.Expression<String> checkout, com.querydsl.core.types.Expression<String> directions, com.querydsl.core.types.Expression<String> rules, com.querydsl.core.types.Expression<String> priceAdd, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.HStatus> hStatus, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.HStep> hStep, com.querydsl.core.types.Expression<String> hNone, com.querydsl.core.types.Expression<String> hname, com.querydsl.core.types.Expression<String> hphone) {
        super(HostInfoDTO.class, new Class<?>[]{int.class, int.class, com.omakase.omastay.vo.AddressVo.class, String.class, com.omakase.omastay.entity.enumurate.HCate.class, String.class, String.class, com.omakase.omastay.vo.HostOwnerInfoVo.class, com.omakase.omastay.vo.HostContactInfoVo.class, String.class, String.class, String.class, String.class, String.class, String.class, com.omakase.omastay.entity.enumurate.HStatus.class, com.omakase.omastay.entity.enumurate.HStep.class, String.class, String.class, String.class}, id, adIdx, addressVo, region, hCate, xAxis, yAxis, hostOwnerInfo, hostContactInfo, hurl, checkin, checkout, directions, rules, priceAdd, hStatus, hStep, hNone, hname, hphone);
    }

    public QHostInfoDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> adIdx, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.HostContactInfoVo> hostContactInfo, com.querydsl.core.types.Expression<String> hurl, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.HStep> hStep, com.querydsl.core.types.Expression<String> hname, com.querydsl.core.types.Expression<String> hphone) {
        super(HostInfoDTO.class, new Class<?>[]{int.class, int.class, com.omakase.omastay.vo.HostContactInfoVo.class, String.class, com.omakase.omastay.entity.enumurate.HStep.class, String.class, String.class}, id, adIdx, hostContactInfo, hurl, hStep, hname, hphone);
    }

    public QHostInfoDTO(com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.HCate> hcate, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.AddressVo> addressVo, com.querydsl.core.types.Expression<String> xaxis, com.querydsl.core.types.Expression<String> yaxis, com.querydsl.core.types.Expression<String> region, com.querydsl.core.types.Expression<String> directions, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.HostOwnerInfoVo> hostOwnerInfo, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.HStep> hstep) {
        super(HostInfoDTO.class, new Class<?>[]{com.omakase.omastay.entity.enumurate.HCate.class, com.omakase.omastay.vo.AddressVo.class, String.class, String.class, String.class, String.class, com.omakase.omastay.vo.HostOwnerInfoVo.class, com.omakase.omastay.entity.enumurate.HStep.class}, hcate, addressVo, xaxis, yaxis, region, directions, hostOwnerInfo, hstep);
    }

}

