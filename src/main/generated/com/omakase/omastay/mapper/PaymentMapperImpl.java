package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Point;
import com.omakase.omastay.entity.enumurate.PayMethod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-20T10:12:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDTO toPaymentDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setIcIdx( paymentIssuedCouponId( payment ) );
        paymentDTO.setPIdx( paymentPointId( payment ) );
        paymentDTO.setAmount( payment.getSalePrice() );
        paymentDTO.setId( payment.getId() );
        paymentDTO.setPayStatus( payment.getPayStatus() );
        if ( payment.getPayMethod() != null ) {
            paymentDTO.setPayMethod( payment.getPayMethod().name() );
        }
        paymentDTO.setPayContent( payment.getPayContent() );
        paymentDTO.setNsalePrice( payment.getNsalePrice() );
        paymentDTO.setCancelContent( payment.getCancelContent() );
        paymentDTO.setPayDate( payment.getPayDate() );
        paymentDTO.setCancelDate( payment.getCancelDate() );
        paymentDTO.setPayNone( payment.getPayNone() );
        paymentDTO.setPaymentKey( payment.getPaymentKey() );

        return paymentDTO;
    }

    @Override
    public Payment toPayment(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setIssuedCoupon( paymentDTOToIssuedCoupon( paymentDTO ) );
        payment.setPoint( paymentDTOToPoint( paymentDTO ) );
        payment.setSalePrice( paymentDTO.getAmount() );
        payment.setId( paymentDTO.getId() );
        payment.setPayStatus( paymentDTO.getPayStatus() );
        if ( paymentDTO.getPayMethod() != null ) {
            payment.setPayMethod( Enum.valueOf( PayMethod.class, paymentDTO.getPayMethod() ) );
        }
        payment.setPayContent( paymentDTO.getPayContent() );
        payment.setNsalePrice( paymentDTO.getNsalePrice() );
        payment.setCancelContent( paymentDTO.getCancelContent() );
        payment.setPayDate( paymentDTO.getPayDate() );
        payment.setCancelDate( paymentDTO.getCancelDate() );
        payment.setPaymentKey( paymentDTO.getPaymentKey() );
        payment.setPayNone( paymentDTO.getPayNone() );

        return payment;
    }

    @Override
    public List<PaymentDTO> toPaymentDTOList(List<Payment> paymentList) {
        if ( paymentList == null ) {
            return null;
        }

        List<PaymentDTO> list = new ArrayList<PaymentDTO>( paymentList.size() );
        for ( Payment payment : paymentList ) {
            list.add( toPaymentDTO( payment ) );
        }

        return list;
    }

    @Override
    public List<Payment> toPaymentList(List<PaymentDTO> paymentDTOList) {
        if ( paymentDTOList == null ) {
            return null;
        }

        List<Payment> list = new ArrayList<Payment>( paymentDTOList.size() );
        for ( PaymentDTO paymentDTO : paymentDTOList ) {
            list.add( toPayment( paymentDTO ) );
        }

        return list;
    }

    private Integer paymentIssuedCouponId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        IssuedCoupon issuedCoupon = payment.getIssuedCoupon();
        if ( issuedCoupon == null ) {
            return null;
        }
        Integer id = issuedCoupon.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer paymentPointId(Payment payment) {
        if ( payment == null ) {
            return null;
        }
        Point point = payment.getPoint();
        if ( point == null ) {
            return null;
        }
        Integer id = point.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected IssuedCoupon paymentDTOToIssuedCoupon(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        IssuedCoupon issuedCoupon = new IssuedCoupon();

        issuedCoupon.setId( paymentDTO.getIcIdx() );

        return issuedCoupon;
    }

    protected Point paymentDTOToPoint(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        Point point = new Point();

        point.setId( paymentDTO.getPIdx() );

        return point;
    }
}
