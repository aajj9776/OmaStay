package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.service.ReservationService;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String reservation() {
        return "reservation/reservation.html";
    }

    @GetMapping("/payment_success")
    public String paymentSuccess(PaymentDTO payment) {

        return "reservation/payment_success.html";
    }

    @PostMapping("/payment_success")
    public String payComplete(PaymentDTO payment, RedirectAttributes redirectAttributes) {
        System.out.println("payment" + payment);

        PaymentDTO res = reservationService.insertPaymentInfo(payment);
        System.out.println("결과" + res);
        if( res.getId() == 0) {
            return "redirect:/reservation/payment_fail";
        }


        //ReservationDTO reservation = reservationService.insertReservationInfo(res);

        redirectAttributes.addAttribute("orderId", payment.getOrderId());
        redirectAttributes.addAttribute("payStatus", payment.getPayStatus());
        redirectAttributes.addAttribute("amount", payment.getAmount());
        redirectAttributes.addAttribute("payContent", payment.getPayContent());
        return "redirect:/reservation/payment_complete";    
    }

    @GetMapping("payment_complete")
    public String payWan() {
        return "reservation/payment_complete.html";
    }
    

    @RequestMapping("/no_reservation")
    public String noReservation() {
        return "reservation/no_reservation.html";
    }


    //이거는 마이페이지로 이동
    @RequestMapping("/reservation_check")
    public String reservationCheck() {
        return "reservation/reservation_check.html";
    }


    @GetMapping("/payment_fail")
    public String fail() {
        return "reservation/payment_fail.html";
    }
    
    
    //이거 2개는 모달창
    @RequestMapping("/room_info")
    public String roomInfo() {
        return "reservation/room_info.html";
    }
    @RequestMapping("/cancel_content")
    public String checkDetail() {
        return "reservation/cancel_content.html";
    }
    
    
}
