package com.omakase.omastay.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.custom.MemberInfoDTO;
import com.omakase.omastay.service.MyPageService;

import ch.qos.logback.core.model.Model;


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

    @GetMapping("/reservation")
    public ModelAndView reservation(@RequestBody(required = false) MemberInfoDTO member) {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("mypage/user-reservation");
        return mv;
    }

    @PostMapping("/reservation")
    @ResponseBody
    public Map<String, Object> reservationProc(@RequestBody(required = false) MemberInfoDTO member, Model model) {
        MemberDTO res = myPageService.getMemberInfo(member.getId());
        System.out.println("예약정보" + res.getReservations());
        Map<String, Object> map = new HashMap<>();
        map.put("member", res);
        map.put("reservation", res.getReservations());
        return map;
    }

    @GetMapping("/reservation_check")
    public ModelAndView reservation_check() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/user-reservation-detail");
        return mv;
    }
    

    @PostMapping("/reservation_check")
    @ResponseBody
    public Map<String, Object> reservationCheckProc(@RequestBody ReservationDTO reservation) {
        System.out.println(reservation.getId());
        PaymentDTO pay = myPageService.getPaymentForReservation(reservation.getId());
        System.out.println("결제정보" + pay);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("payment", pay);
        return map;
    }
}
