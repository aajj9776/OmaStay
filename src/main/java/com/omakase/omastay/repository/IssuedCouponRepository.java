package com.omakase.omastay.repository;

import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.repository.custom.IssuedCouponRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Integer>, IssuedCouponRepositoryCustom {

    boolean existsByIcCode(String icCode);

    @Query("SELECT ic FROM IssuedCoupon ic WHERE ic.coupon.id = :cp_idx")
    List<IssuedCoupon> findByCouponId(@Param("cp_idx") Integer cp_idx);

}