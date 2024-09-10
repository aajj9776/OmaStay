package com.omakase.omastay.service;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


}
