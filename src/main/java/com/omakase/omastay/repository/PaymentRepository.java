package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.repository.custom.PaymentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer>, PaymentRepositoryCustom {

}