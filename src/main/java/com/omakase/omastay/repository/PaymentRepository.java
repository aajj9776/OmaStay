package com.omakase.omastay.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.repository.custom.PaymentRepositoryCustom;
import jakarta.persistence.LockModeType;


public interface PaymentRepository extends JpaRepository<Payment, Integer>, PaymentRepositoryCustom {

     // 특정 결제 정보를 비관적 락(PESSIMISTIC_WRITE)으로 조회
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Payment p WHERE p.paymentKey = :paymentKey")
    Payment findByPaymentKeyWithLock(@Param("paymentKey") String paymentKey);

}