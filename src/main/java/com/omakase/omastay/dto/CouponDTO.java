package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.entity.enumurate.CpCate;
import com.omakase.omastay.entity.enumurate.CpMethod;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CouponDTO {
    private Integer id;
    private String cpContent;
    private StartEndVo cpStartEnd = new StartEndVo();
    private String cpSale;
    private CpCate cpCate;
    private CpMethod cpMethod;
    private String cpNone;

    public CouponDTO(Coupon coupon) {
        this.id = coupon.getId();
        this.cpContent = coupon.getCpContent();
        this.cpStartEnd = coupon.getCpStartEnd();
        this.cpSale = coupon.getCpSale();
        this.cpCate = coupon.getCpCate();
        this.cpMethod = coupon.getCpMethod();
        this.cpNone = coupon.getCpNone();
    }

    @QueryProjection
    public CouponDTO(Integer id, String cpContent, StartEndVo cpStartEnd, String cpSale, CpCate cpCate, CpMethod cpMethod, String cpNone) {
        this.id = id;
        this.cpContent = cpContent;
        this.cpStartEnd = cpStartEnd;
        this.cpSale = cpSale;
        this.cpCate = cpCate;
        this.cpMethod = cpMethod;
        this.cpNone = cpNone;
    }
}