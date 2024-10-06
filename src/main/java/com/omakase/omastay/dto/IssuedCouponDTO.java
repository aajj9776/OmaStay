package com.omakase.omastay.dto;

import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.entity.enumurate.IcStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IssuedCouponDTO {
    private Integer id;
    private Integer mIdx;
    private Integer cpIdx;
    private IcStatus icStatus;
    private String icCode;
    private String icNone;
    private CouponDTO coupon;

    public IssuedCouponDTO(IssuedCoupon issuedCoupon) {
        this.id = issuedCoupon.getId();
        this.mIdx = issuedCoupon.getMember() != null ? issuedCoupon.getMember().getId() : null;
        this.cpIdx = issuedCoupon.getCoupon() != null ? issuedCoupon.getCoupon().getId() : null;
        this.icStatus = issuedCoupon.getIcStatus();
        this.icCode = issuedCoupon.getIcCode();
        this.icNone = issuedCoupon.getIcNone();
        this.coupon = issuedCoupon.getCoupon() != null ? new CouponDTO(issuedCoupon.getCoupon()) : null;
    }

    @QueryProjection
    public IssuedCouponDTO(Integer id, Integer mIdx, Integer cpIdx, IcStatus icStatus, String icCode, String icNone) {
        this.id = id;
        this.mIdx = mIdx;
        this.cpIdx = cpIdx;
        this.icStatus = icStatus;
        this.icCode = icCode;
        this.icNone = icNone;
    }
}