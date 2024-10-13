package com.omakase.omastay.controller;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.dto.custom.MemberInfoDTO;
import com.omakase.omastay.service.IssuedCouponService;
import com.omakase.omastay.service.PointService;

@Controller
public class PointCouponController {

    @Autowired
    private IssuedCouponService issuedCouponService;

    @Autowired
    private PointService pointService;


    @PostMapping("/coupon")
    @ResponseBody
    public Map<String,Object> couponInfo(MemberInfoDTO member) {
        Map<String, Object> map = new HashMap<>();
        
        List<IssuedCouponDTO> coupon = issuedCouponService.getCouponPoint(member.getId());
        if( coupon != null && coupon.size() > 0){
            System.out.println("쿠폰" + coupon);
            map.put("coupon", coupon);
        } 
        
        return map;
    }

    @PostMapping("/coupon/use")
    @ResponseBody
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

    @PostMapping("/point")
    @ResponseBody
    public Map<String, Object> pointInfo(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<>();

        List<PointDTO> point = pointService.getPoint(id);
        Integer sum = pointService.getSumPoint(id);
        if(point != null && point.size() > 0){
            System.out.println("포인트 내역" + point);
            System.out.println("최종값" + sum);
            map.put("point", point);
            map.put("sum", sum);
        } else {
            map.put("point", false);
        }

        return map;
    }

    @PostMapping("/point/use")
    @ResponseBody
    public Map<String, Object> usePoint(PointDTO pointDTO) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("포인트 사용" + pointDTO);
        PointDTO res = pointService.savePoint(pointDTO);
        System.out.println("사용한 포인트" + res);
        String result = "";
        if (res != null) {
            result = "success";
        } else {
            result = "fail";
        }
        map.put("result", result);
        map.put("point", res);
        
        return map;
    }
    
    

    
    
}
