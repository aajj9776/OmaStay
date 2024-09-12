package com.omakase.omastay.repository;

import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.repository.custom.IssuedCouponRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Integer>, IssuedCouponRepositoryCustom {

    boolean existsByIcCode(String icCode);

    List<IssuedCoupon> findByCouponId(Integer cp_idx);

}