package com.omakase.omastay.repository.custom.impl;

import java.util.List;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.dto.QCouponDTO;
import com.omakase.omastay.dto.QIssuedCouponDTO;
import com.omakase.omastay.dto.custom.CouponHistoryDTO;
import com.omakase.omastay.dto.custom.QCouponHistoryDTO;
import com.omakase.omastay.entity.QCoupon;
import com.omakase.omastay.entity.QIssuedCoupon;
import com.omakase.omastay.entity.QPayment;
import com.omakase.omastay.entity.enumurate.IcStatus;
import com.omakase.omastay.repository.custom.IssuedCouponRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class IssuedCouponRepositoryImpl implements IssuedCouponRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public IssuedCouponRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<CouponHistoryDTO> findCouponHistoryByCpIdx(Integer cpIdx) {
        QIssuedCoupon issuedCoupon = QIssuedCoupon.issuedCoupon;
        QCoupon coupon = QCoupon.coupon;
        QPayment payment = QPayment.payment;

        return queryFactory
            .select(new QCouponHistoryDTO(
                new QIssuedCouponDTO(issuedCoupon.id, issuedCoupon.member.id, issuedCoupon.coupon.id, issuedCoupon.icStatus, issuedCoupon.icCode, issuedCoupon.icNone),
                new QCouponDTO(coupon.id, coupon.cpContent, coupon.cpStartEnd, coupon.cpSale, coupon.cpCate, coupon.cpMethod, coupon.cpNone),
                payment.id,  // Payment의 id만 선택
                payment.payDate
            ))
            .from(issuedCoupon)
            .join(coupon).on(issuedCoupon.coupon.id.eq(coupon.id))
            .leftJoin(payment).on(issuedCoupon.id.eq(payment.issuedCoupon.id).and(issuedCoupon.icStatus.eq(IcStatus.USED)))  // Payment와 조인
            .where(issuedCoupon.coupon.id.eq(cpIdx))
            .fetch();
    }
}
