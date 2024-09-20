package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.service.IssuedCouponService;


@Controller
public class PointCouponController {

    @Autowired
    private IssuedCouponService issuedCouponService;

    @PostMapping("/coupon/use")
    public String useCoupon(IssuedCouponDTO issuedCouponDTO) {
        System.out.println("useCoupon" + issuedCouponDTO.getId());
        IssuedCouponDTO res = issuedCouponService.useCoupon(issuedCouponDTO);
        String result = "";
        if (res != null) {
            result = "success";
        } else {
            result = "fail";
        }

        return result;
        
    }
    
    
}
