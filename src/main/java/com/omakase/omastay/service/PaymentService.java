package com.omakase.omastay.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.custom.CancelRequestDTO;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.repository.PaymentRepository;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public PaymentDTO getPayment(CancelRequestDTO cancelRequest) {
        Payment payment = paymentRepository.findById(cancelRequest.getPayIdx()).get();
        payment.setPayStatus(PayStatus.CANCEL);
        payment.setCancelContent(cancelRequest.getCancelReason());
        payment.setCancelDate(LocalDateTime.now());
        Payment res = paymentRepository.save(payment);
        PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toPaymentDTO(res);
        return paymentDTO;
    }

}
