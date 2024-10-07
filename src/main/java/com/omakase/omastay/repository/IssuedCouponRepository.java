package com.omakase.omastay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.repository.custom.IssuedCouponRepositoryCustom;

public interface IssuedCouponRepository extends JpaRepository<IssuedCoupon, Integer>, IssuedCouponRepositoryCustom {

    boolean existsByIcCode(String icCode);

    @Query("SELECT ic FROM IssuedCoupon ic WHERE ic.coupon.id = :cp_idx")
    List<IssuedCoupon> findByCouponId(@Param("cp_idx") Integer cp_idx);

    
    @Query("SELECT ic FROM IssuedCoupon ic " +
    "JOIN ic.coupon c " +
    "WHERE ic.member.id = :memberId " +
    "AND c.cpStartEnd.end > CURRENT_DATE " +
    "AND ic.icStatus = UNUSED")
    List<IssuedCoupon> findValidCouponsByMemberId(@Param("memberId") int memberId);

    @Query("SELECT ic FROM IssuedCoupon ic WHERE ic.id =:icIdx AND ic.member.id = :memIdx")
    IssuedCoupon findByIdAndMemIdx(@Param("icIdx") Integer icIdx, @Param("memIdx") Integer memIdx);

}