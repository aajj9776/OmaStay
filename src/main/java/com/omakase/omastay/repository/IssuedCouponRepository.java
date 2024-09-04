package com.omakase.omastay.repository;

import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.repository.custom.IssuedCouponRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Integer>, IssuedCouponRepositoryCustom {

}