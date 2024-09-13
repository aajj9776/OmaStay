package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {
    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "issuedCoupon.id", target = "icIdx")
    @Mapping(source = "point.id", target = "PIdx")
    @Mapping(source = "salePrice", target = "amount")
    @Mapping(target = "orderId", ignore = true)
    PaymentDTO toPaymentDTO(Payment payment);

    @Mapping(source = "icIdx", target = "issuedCoupon.id")
    @Mapping(source = "PIdx", target = "point.id")
    @Mapping(source = "amount", target = "salePrice")
    Payment toPayment(PaymentDTO paymentDTO);

    List<PaymentDTO> toPaymentDTOList(List<Payment> paymentList);

    List<Payment> toPaymentList(List<PaymentDTO> paymentDTOList);
}