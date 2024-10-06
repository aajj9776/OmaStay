package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.dto.IssuedCouponDTO;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CouponHistoryDTO {
    private IssuedCouponDTO issuedCouponDTO;
    private CouponDTO couponDTO;
    private Integer paymentId;  
    private LocalDateTime payTime;

    @QueryProjection
    public CouponHistoryDTO(IssuedCouponDTO issuedCouponDTO, CouponDTO couponDTO, Integer paymentId, LocalDateTime payTime) {
        this.issuedCouponDTO = issuedCouponDTO;
        this.couponDTO = couponDTO;
        this.paymentId = paymentId; 
        this.payTime = payTime;
    }
}
