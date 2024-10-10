package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.MemberDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class MemberCouponDTO {
    private MemberDTO member;                         // 회원 정보
    private List<FormattedCouponDTO> formattedCoupons; // 포맷된 쿠폰 리스트

    public MemberCouponDTO(MemberDTO member, List<CouponIssuedCouponDTO> coupons) {
        this.member = member;
        this.formattedCoupons = formatCoupons(coupons);
    }

    // 쿠폰 리스트를 포맷된 문자열 리스트로 변환
    private List<FormattedCouponDTO> formatCoupons(List<CouponIssuedCouponDTO> coupons) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        return coupons.stream()
            .map(coupon -> {
                // 남은 일수 계산
                long daysRemaining = ChronoUnit.DAYS.between(LocalDateTime.now(), coupon.getCouponEndtime());
                // 시작일 및 만료일을 포맷
                String formattedStartDate = coupon.getCouponStarttime().format(formatter);
                String formattedEndDate = coupon.getCouponEndtime().format(formatter);

                return new FormattedCouponDTO(
                    coupon.getCouponId(),
                    coupon.getCouponContent(),
                    coupon.getCouponSale(),
                    formattedStartDate,       // 시작일
                    formattedEndDate,         // 만료일
                    daysRemaining,            // 남은 일수
                    coupon.getIcStatus(),
                    coupon.getIcCode()
                );
            })
            .collect(Collectors.toList());
    }

    @Data
    @NoArgsConstructor
    public static class FormattedCouponDTO {
        private Integer couponId;
        private String couponContent;
        private String couponSale;
        private String couponStarttime;
        private String couponEndtime;
        private long daysRemaining;
        private String icStatus;
        private String icCode;

        public FormattedCouponDTO(Integer couponId, String couponContent, String couponSale, String couponStarttime, String couponEndtime, long daysRemaining, String icStatus, String icCode) {
            this.couponId = couponId;
            this.couponContent = couponContent;
            this.couponSale = couponSale;
            this.couponStarttime = couponStarttime;
            this.couponEndtime = couponEndtime;
            this.daysRemaining = daysRemaining;
            this.icStatus = icStatus;
            this.icCode = icCode;
        }
    }
}
