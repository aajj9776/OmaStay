package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.repository.custom.CouponRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer>, CouponRepositoryCustom {

}