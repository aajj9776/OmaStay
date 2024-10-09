package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.repository.custom.CouponRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Integer>, CouponRepositoryCustom {
    @Query("SELECT c FROM Coupon c ORDER BY c.id DESC")
    List<Coupon> findAll();
}