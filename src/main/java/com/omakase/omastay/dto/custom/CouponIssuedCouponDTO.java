package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime; // java.time 패키지를 사용

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
}
