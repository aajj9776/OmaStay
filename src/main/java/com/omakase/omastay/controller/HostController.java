package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/host")
public class HostController {

    @GetMapping()
    public String host() {
        return "host/host_login";
    }

    @RequestMapping("/register")
    public String hostRegister() {
        return "host/host_register";
    }

    @RequestMapping("/findpw")
    public String hostfindpw() {
        return "host/host_findpw";
    }


    @RequestMapping("/faq")
    public String hostfaq() {
        return "host/host_faq";
    }

    @RequestMapping("/info")
    public String hostinfo() {
        return "host/host_info";
    }

    @RequestMapping("/inquiry")
    public String hostinquiry() {
        return "host/host_inquiry";
    }

    @RequestMapping("/inquirydetail")
    public String hostinquirydetail() {
        return "host/host_inquirydetail";
    }

    @RequestMapping("/inquiryreg")
    public String hostinquiryreg() {
        return "host/host_inquiryreg";
    }

    @RequestMapping("/main")
    public String hostmain() {
        return "host/host_main";
    }

    @RequestMapping("/mypage")
    public String hostmypage() {
        return "host/host_mypage";
    }

    @RequestMapping("/notice")
    public String hostnotice() {
        return "host/host_notice";
    }

    @RequestMapping("/noticedetail")
    public String hostnoticedetail() {
        return "host/host_noticedetail";
    }

    @RequestMapping("/payment")
    public String hostpayment() {
        return "host/host_payment";
    }

    @RequestMapping("/paymentdetail")
    public String hostpaymentdetail() {
        return "host/host_paymentdetail";
    }
    
    @RequestMapping("/reg")
    public String hostreg() {
        return "host/host_reg";
    }
    
    @RequestMapping("/reservation")
    public String hostreservation() {
        return "host/host_reservation";
    }

    @RequestMapping("/reservationdetail")
    public String hostreservationdetail() {
        return "host/host_reservationdetail";
    }

    @RequestMapping("/review")
    public String hostreview() {
        return "host/host_review";
    }

    @RequestMapping("/reviewdetail")
    public String hostreviewdetail() {
        return "host/host_reviewdetail";
    }

    @RequestMapping("/roomlist")
    public String hostroomlist() {
        return "host/host_roomlist";
    }

    @RequestMapping("/roomreg")
    public String hostroomreg() {
        return "host/host_roomreg";
    }

    @RequestMapping("/rules")
    public String hostrules() {
        return "host/host_rules";
    }

    @RequestMapping("/sales")
    public String hostsales() {
        return "host/host_sales";
    }
}
