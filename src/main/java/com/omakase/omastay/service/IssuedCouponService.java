package com.omakase.omastay.service;

import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.mapper.IssuedCouponMapper;
import com.omakase.omastay.repository.IssuedCouponRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuedCouponService {

    @Autowired
    private IssuedCouponRepository issuedCouponRepository;

    public List<IssuedCouponDTO> getIssuedCouponsById(Integer cp_idx){
        List<IssuedCoupon> issuedCoupons = issuedCouponRepository.findByCouponId(cp_idx);
        return IssuedCouponMapper.INSTANCE.toIssuedCouponDTOList(issuedCoupons);
    }

}
