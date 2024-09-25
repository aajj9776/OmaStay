package com.omakase.omastay.dto;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.entity.enumurate.HStatus;
import com.omakase.omastay.entity.enumurate.HStep;
import com.omakase.omastay.vo.AddressVo;
import com.omakase.omastay.vo.HostContactInfoVo;
import com.omakase.omastay.vo.HostOwnerInfoVo;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HostInfoDTO {
    private Integer id;
    private Integer adIdx;
    private AddressVo addressVo = new AddressVo();
    private String region;
    private HCate hCate; // 추가된 필드
    private String xAxis;
    private String yAxis;
    private HostOwnerInfoVo hostOwnerInfo  = new HostOwnerInfoVo();
    private HostContactInfoVo hostContactInfo = new HostContactInfoVo();
    private String hurl;
    private String checkin;
    private String checkout;
    private String directions;
    private String rules;
    private String priceAdd;
    private HStatus hStatus;
    private HStep hStep;
    private String hNone;
    private String hname;
    private String hphone;

    public HostInfoDTO(HostInfo hostInfo) {
        this.id = hostInfo.getId();
        this.adIdx = hostInfo.getAdminMember() != null ? hostInfo.getAdminMember().getId() : null;
        this.addressVo = hostInfo.getHostAddress();
        this.region = hostInfo.getRegion();
        this.hCate = hostInfo.getHCate(); // 추가된 필드
        this.xAxis = hostInfo.getXAxis();
        this.yAxis = hostInfo.getYAxis();
        this.hostOwnerInfo = hostInfo.getHostOwnerInfo();
        this.hostContactInfo = hostInfo.getHostContactInfo();
        this.hurl = hostInfo.getHurl();
        this.checkin = hostInfo.getCheckin();
        this.checkout = hostInfo.getCheckout();
        this.directions = hostInfo.getDirections();
        this.rules = hostInfo.getRules();
        this.priceAdd = hostInfo.getPriceAdd();
        this.hStatus = hostInfo.getHStatus();
        this.hStep = hostInfo.getHStep();
        this.hNone = hostInfo.getHNone();
        this.hname = hostInfo.getHname();
        this.hphone = hostInfo.getHphone();
    }

    @QueryProjection
    public HostInfoDTO(Integer id, Integer adIdx, AddressVo addressVo, String region, HCate hCate, String xAxis, String yAxis, HostOwnerInfoVo hostOwnerInfo, HostContactInfoVo hostContactInfo, String hurl, String checkin, String checkout, String directions, String rules, String priceAdd, HStatus hStatus, HStep hStep, String hNone, String hname, String hphone) {
        this.id = id;
        this.adIdx = adIdx;
        this.addressVo = addressVo;
        this.region = region;
        this.hCate = hCate;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.hostOwnerInfo = hostOwnerInfo;
        this.hostContactInfo = hostContactInfo;
        this.hurl = hurl;
        this.checkin = checkin;
        this.checkout = checkout;
        this.directions = directions;
        this.rules = rules;
        this.priceAdd = priceAdd;
        this.hStatus = hStatus;
        this.hStep = hStep;
        this.hNone = hNone;
        this.hname = hname;
        this.hphone = hphone;
    }

    @QueryProjection
    public HostInfoDTO(Integer id, Integer adIdx, HostContactInfoVo hostContactInfo, String hurl, HStep hStep, String hname, String hphone) {
        this.id = id;
        this.adIdx = adIdx;
        this.hostContactInfo = hostContactInfo;
        this.hurl = hurl;
        this.hStep = hStep;
        this.hname = hname;
        this.hphone = hphone;
    }

    @QueryProjection
    public HostInfoDTO(HCate hcate, AddressVo addressVo, String xaxis, String yaxis, String region, String directions, HostOwnerInfoVo hostOwnerInfo, HStep hstep)  {
        this.hCate = hcate;
        this.addressVo = addressVo;
        this.xAxis = xaxis;
        this.yAxis = yaxis;
        this.region = region;
        this.directions = directions;
        this.hostOwnerInfo = hostOwnerInfo;
        this.hStep = hstep;
    }
}