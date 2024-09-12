package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.custom.MemberInfoDTO;
import com.omakase.omastay.service.MyPageService;


@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MyPageService myPageService;

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
    @RequestMapping("/info")
    public String userMyPageInfo() {
        return "mypage/user-mypage-info";
    }

    @RequestMapping("/reservation")
    public ModelAndView reservation() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/user-reservation");
        return mv;
    }

    @RequestMapping("/reservation_check")
    public ModelAndView reservationCheck(@RequestBody MemberInfoDTO member) {
        System.out.println("회원번호"+ member);
        ModelAndView mv = new ModelAndView();

        // MemberDTO res = myPageService.getMemberInfo(member.getId());
        // System.out.println(res);
        // mv.addObject("info", res);
        mv.setViewName("mypage/user-reservation-detail");
        return mv;
    }
    
    
}
