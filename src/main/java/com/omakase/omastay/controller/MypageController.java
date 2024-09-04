package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @RequestMapping("/member-ship")
    public String userMemberShip() {
        return "mypage/user-member-ship";
    }

    @RequestMapping("/coupon")
    public String userMypageCoupon() {
        return "mypage/user-mypage-coupon";
    }

    @RequestMapping("/point")
    public String userMypagePoint() {
        return "mypage/user-mypage-point";
    }

    @RequestMapping("/review")
    public String userMyPageReview() {
        return "mypage/user-my-page-review";
    }
}
