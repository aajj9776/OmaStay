package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @GetMapping
    public String reservation() {
        return "reservation/reservation.html";
    }

    @RequestMapping("/payment_success")
    public String paymentSuccess() {
        return "reservation/payment_success.html";
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
