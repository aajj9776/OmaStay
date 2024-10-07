package com.omakase.omastay.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.repository.custom.PaymentRepositoryCustom;


public interface PaymentRepository extends JpaRepository<Payment, Integer>, PaymentRepositoryCustom {

    Payment findByPaymentKey(String paymentKey);

}