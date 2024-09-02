package com.omakase.omastay.dto;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.enumurate.HStatus;
import com.omakase.omastay.vo.AddressVo;
import com.omakase.omastay.vo.HostContactInfoVo;
import com.omakase.omastay.vo.HostOwnerInfoVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostInfoDTO {
    private int id;
    private int adIdx;
    private AddressVo addressVo;
    private String region;
    private String xAxis;
    private String yAxis;
    private HostOwnerInfoVo hostContactInfo;
    private HostContactInfoVo hostOwnerInfo;
    private String hUrl;
    private String checkin;
    private String checkout;
    private String directions;
    private String rules;
    private String priceAdd;
    private HStatus hStatus;
    private String hNone;

    public HostInfoDTO(HostInfo hostInfo) {
        this.id = hostInfo.getId();
        this.adIdx = hostInfo.getAdminMember() != null ? hostInfo.getAdminMember().getId() : null;
        this.addressVo = hostInfo.getAddressVo();
        this.region = hostInfo.getRegion();
        this.xAxis = hostInfo.getXAxis();
        this.yAxis = hostInfo.getYAxis();
        this.hostContactInfo = hostInfo.getHostContactInfo();
        this.hostOwnerInfo = hostInfo.getHostOwnerInfo();
        this.hUrl = hostInfo.getHUrl();
        this.checkin = hostInfo.getCheckin();
        this.checkout = hostInfo.getCheckout();
        this.directions = hostInfo.getDirections();
        this.rules = hostInfo.getRules();
        this.priceAdd = hostInfo.getPriceAdd();
        this.hStatus = hostInfo.getHStatus();
        this.hNone = hostInfo.getHNone();
    }

    @QueryProjection
    public HostInfoDTO(int id, int adIdx, AddressVo addressVo, String region, String xAxis, String yAxis,
                       HostOwnerInfoVo hostContactInfo, HostContactInfoVo hostOwnerInfo, String hUrl,
                       String checkin, String checkout, String directions, String rules, String priceAdd, HStatus hStatus, String hNone) {
        this.id = id;
        this.adIdx = adIdx;
        this.addressVo = addressVo;
        this.region = region;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.hostContactInfo = hostContactInfo;
        this.hostOwnerInfo = hostOwnerInfo;
        this.hUrl = hUrl;
        this.checkin = checkin;
        this.checkout = checkout;
        this.directions = directions;
        this.rules = rules;
        this.priceAdd = priceAdd;
        this.hStatus = hStatus;
        this.hNone = hNone;
    }
}