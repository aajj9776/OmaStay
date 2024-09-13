package com.omakase.omastay.dto;

import java.time.LocalDateTime;

import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {
    private int id;
    private int icIdx;
    private int pIdx;
    private PayStatus payStatus;
    private String payMethod;
    private String payContent;
    private String salePrice;
    private String nsalePrice;
    private String cancelTime;
    private String cancelContent;
    private LocalDateTime payDate;
    private LocalDateTime cancelDate;
    private String payNone;

    //주문번호
    private String paymentKey;
    private String orderId;
    private String amount;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.icIdx = payment.getIssuedCoupon() != null ? payment.getIssuedCoupon().getId() : null;
        this.pIdx = payment.getPoint() != null ? payment.getPoint().getId() : null;
        this.payStatus = payment.getPayStatus();
        this.payMethod = payment.getPayMethod().toString();
        this.payContent = payment.getPayContent();
        this.salePrice = payment.getSalePrice();
        this.nsalePrice = payment.getNsalePrice();
        this.cancelContent = payment.getCancelContent();
        this.payDate = payment.getPayDate();
        this.cancelDate = payment.getCancelDate();
        this.paymentKey = payment.getPaymentKey();
        this.payNone = payment.getPayNone();
    }

    @QueryProjection
    public PaymentDTO(int id, int icIdx, int pIdx, PayStatus payStatus, String payMethod, String payContent, String salePrice, String nsalePrice, String cancelContent, LocalDateTime payDate, LocalDateTime cancelDate, String paymentKey, String payNone) {
        this.id = id;
        this.icIdx = icIdx;
        this.pIdx = pIdx;
        this.payStatus = payStatus;
        this.payMethod = payMethod;
        this.payContent = payContent;
        this.salePrice = salePrice;
        this.nsalePrice = nsalePrice;
        this.cancelContent = cancelContent;
        this.payDate = payDate;
        this.cancelDate = cancelDate;
        this.paymentKey = paymentKey;
        this.payNone = payNone;
    }
}