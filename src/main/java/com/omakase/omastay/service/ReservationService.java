package com.omakase.omastay.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.repository.PaymentRepository;

@Service
public class ReservationService {

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentDTO insertPaymentInfo(PaymentDTO payment) {
        System.out.println("얍"+ payment.getNsalePrice());

        Payment res = PaymentMapper.INSTANCE.toPayment(payment);

        res.setIssuedCoupon(null);
        res.setPoint(null);

        if( payment.getNsalePrice() == null && payment.getNsalePrice().equals("")){
            res.setNsalePrice("0");
        }

        res.setPayStatus(PayStatus.PAY);
        res.setPayDate(LocalDateTime.now());

        Payment pay = paymentRepository.save(res);

        PaymentDTO dto = PaymentMapper.INSTANCE.toPaymentDTO(pay);
        return dto;
    }


}
