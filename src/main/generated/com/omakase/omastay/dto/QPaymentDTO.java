package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QPaymentDTO is a Querydsl Projection type for PaymentDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPaymentDTO extends ConstructorExpression<PaymentDTO> {

    private static final long serialVersionUID = 1503715082L;

    public QPaymentDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> icIdx, com.querydsl.core.types.Expression<Integer> pIdx, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.PayStatus> payStatus, com.querydsl.core.types.Expression<String> payMethod, com.querydsl.core.types.Expression<String> payContent, com.querydsl.core.types.Expression<String> salePrice, com.querydsl.core.types.Expression<String> nsalePrice, com.querydsl.core.types.Expression<String> cancelContent, com.querydsl.core.types.Expression<java.time.LocalDateTime> payDate, com.querydsl.core.types.Expression<java.time.LocalDateTime> cancelDate, com.querydsl.core.types.Expression<String> paymentKey, com.querydsl.core.types.Expression<String> payNone) {
        super(PaymentDTO.class, new Class<?>[]{int.class, int.class, int.class, com.omakase.omastay.entity.enumurate.PayStatus.class, String.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class, String.class, String.class}, id, icIdx, pIdx, payStatus, payMethod, payContent, salePrice, nsalePrice, cancelContent, payDate, cancelDate, paymentKey, payNone);
    }

}

