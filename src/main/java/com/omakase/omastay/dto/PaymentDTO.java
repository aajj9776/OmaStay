package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.enumurate.PayMethod;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentDTO {
    private int id;
    private int icIdx;
    private int pIdx;
    private PayStatus payStatus;
    private PayMethod payMethod;
    private String payContent;
    private String salePrice;
    private String nsalePrice;
    private String cancelTime;
    private String cancelContent;
    private LocalDateTime payDate;
    private LocalDateTime cancelDate;
    private String payNone;

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.icIdx = payment.getIssuedCoupon() != null ? payment.getIssuedCoupon().getId() : null;
        this.pIdx = payment.getPoint() != null ? payment.getPoint().getId() : null;
        this.payStatus = payment.getPayStatus();
        this.payMethod = payment.getPayMethod();
        this.payContent = payment.getPayContent();
        this.salePrice = payment.getSalePrice();
        this.nsalePrice = payment.getNsalePrice();
        this.cancelTime = payment.getCancelTime();
        this.cancelContent = payment.getCancelContent();
        this.payDate = payment.getPayDate();
        this.cancelDate = payment.getCancelDate();
        this.payNone = payment.getPayNone();
    }

    @QueryProjection
    public PaymentDTO(int id, int icIdx, int pIdx, PayStatus payStatus, PayMethod payMethod, String payContent, String salePrice, String nsalePrice, String cancelTime, String cancelContent, LocalDateTime payDate, LocalDateTime cancelDate, String payNone) {
        this.id = id;
        this.icIdx = icIdx;
        this.pIdx = pIdx;
        this.payStatus = payStatus;
        this.payMethod = payMethod;
        this.payContent = payContent;
        this.salePrice = salePrice;
        this.nsalePrice = nsalePrice;
        this.cancelTime = cancelTime;
        this.cancelContent = cancelContent;
        this.payDate = payDate;
        this.cancelDate = cancelDate;
        this.payNone = payNone;
    }
}