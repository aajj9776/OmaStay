package com.omakase.omastay.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omakase.omastay.dto.NonMemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.custom.MemberInfoDTO;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.service.EmailService;
import com.omakase.omastay.service.IssuedCouponService;
import com.omakase.omastay.service.NonMemberService;
import com.omakase.omastay.service.ReservationService;

import jakarta.mail.MessagingException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private IssuedCouponService issuedCouponService;

    @Autowired
    private NonMemberService nonMemberService;

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ModelAndView reservation(MemberInfoDTO member) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reservation/reservation");
        return mv;
    }

    @GetMapping("/payment_success")
    public String paymentSuccess(PaymentDTO payment) {

        return "reservation/payment_success.html";
    }

    @RequestMapping("/sendEmail")  
    public ResponseEntity<String> sendEmailPath(@RequestParam("email") String email) throws MessagingException { 
        try {
            emailService.sendEmail(email);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");  
    }

    @RequestMapping("/emailCheck")  
    public ResponseEntity<String> sendEmailAndCode(@RequestParam("email") String email, @RequestParam("code") String code){  

        System.out.println(email);
        if (emailService.verifyEmailCode(email, code)) {  
            return ResponseEntity.ok("success");  
        }  
        return ResponseEntity.notFound().build();  
    }


    @PostMapping("/payment_success")
    public String payComplete(PaymentDTO payment, RedirectAttributes redirectAttributes, ReservationDTO reservation, NonMemberDTO nonMember) {
        System.out.println("payment" + payment);

        System.out.println("reservation이메일 이름 들어와야함" + reservation);

        ReservationDTO check = reservationService.checkReservation(reservation);
        if( check != null){
            return "redirect:/reservation/error";
        }

        //결제정보 저장
        PaymentDTO res = reservationService.insertPaymentInfo(payment);
        System.out.println("결과" + res);
        if( res == null) {
            return "redirect:/reservation/payment_fail";
        }

        //예약정보 저장
        ReservationDTO reserve = null;
        if( res.getPayStatus() == PayStatus.PAY) {
            reservation.setPayIdx(res.getId());

            if( nonMember != null){
                NonMemberDTO dt = nonMemberService.insertNonMember(nonMember);
                reserve = reservationService.insertReservationInfo(reservation, res, dt);
            } else {
                reserve = reservationService.insertReservationInfo(reservation, res, nonMember);
            }

            System.out.println("예약결과" + reserve);
        } else {
            return "redirect:/reservation/payment_fail";
        }
        if( reserve != null){
            if( reserve.getResStatus() == ResStatus.PENDING) {
                redirectAttributes.addAttribute("orderId", reserve.getResNum());
                redirectAttributes.addAttribute("payStatus", payment.getPayStatus());
                redirectAttributes.addAttribute("amount", reserve.getResPrice());
                redirectAttributes.addAttribute("payContent", payment.getPayContent());
                return "redirect:/reservation/payment_complete";  
            } else {
                return "redirect:/reservation/payment_fail";
            }
        } else {
            return "redirect:/reservation/payment_fail";
        }
        
    }

    @GetMapping("/payment_complete")
    public String payWan() {
        return "reservation/payment_complete.html";
    }
    


    @RequestMapping("/check_detail")
    public ModelAndView check_detail() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reservation/check_detail.html");

        return mv;
    }

    //이거는 마이페이지로 이동
    @RequestMapping("/reservation_check")
    public ModelAndView reservationCheck() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("reservation/reservation_check.html");
        return mv;
        
    }


    @GetMapping("/payment_fail")
    public String fail() {
        return "reservation/payment_fail.html";
    }
   
    @GetMapping("/modal/coupon-modal")
    public String getCouponModal() {
        return "reservation/modal/coupon-modal"; // .html 확장자는 생략 가능
    }

    @GetMapping("error")
    public String error() {
        return "reservation/error";
    }

    @GetMapping("/noReservation")
    public ModelAndView noReservation() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/reservation/no_login");
        return mv;
    }

    @PostMapping("/noReservation")
    @ResponseBody
    public Map<String, Object> postMethodName(NonMemberDTO nonMember) {
        System.out.println("nonMember" + nonMember);
        Map<String, Object> map = new HashMap<>();
        
        return map;
    }

}
