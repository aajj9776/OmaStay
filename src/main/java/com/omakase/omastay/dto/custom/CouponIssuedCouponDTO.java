package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CouponIssuedCouponDTO {
    private Integer couponId;        // 쿠폰 ID
    private String couponContent;    // 쿠폰 내용
    private String couponSale;       // 쿠폰 할인 (금액 또는 퍼센트)
    private LocalDateTime couponStarttime;  // 쿠폰 시작일
    private LocalDateTime couponEndtime;    // 쿠폰 만료일
    private String icStatus;         // 발행된 쿠폰 상태 (미사용, 사용)
    private String icCode;           // 쿠폰 코드
    private String cpCate;           // 쿠폰 카테고리 (PRICE 또는 PERCENT)

    // 모든 필드를 받는 생성자
    public CouponIssuedCouponDTO(Integer couponId, String couponContent, String couponSale, LocalDateTime couponStarttime,
                                 LocalDateTime couponEndtime, String icStatus, String icCode, String cpCate) {
        this.couponId = couponId;
        this.couponContent = couponContent;
        this.couponSale = couponSale;
        this.couponStarttime = couponStarttime;
        this.couponEndtime = couponEndtime;
        this.icStatus = icStatus;
        this.icCode = icCode;
        this.cpCate = cpCate;
    }
}
