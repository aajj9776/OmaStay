package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omakase.omastay.service.AdminMemberService;
import com.omakase.omastay.service.EmailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/host")
@RequiredArgsConstructor  
public class HostController {

    @Autowired
    private AdminMemberService adminMemberService;

    private final EmailService emailService;  

    @RequestMapping("/login")
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

    @RequestMapping("/idcheck")
    @ResponseBody
    public int hostidcheck(@RequestParam("id") String id) {

        int cnt = adminMemberService.hostidcheck(id);

        if(cnt == 0){
            return 0;
        }else{
            return 1;
        }
    }

    @RequestMapping("/emailsend")  
    public ResponseEntity<String> sendEmailPath(@RequestParam("email") String email) throws MessagingException { 
        try {
            emailService.sendEmail(email);  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("success");  
    }  
  
    @RequestMapping("/emailchecknum")  
    public ResponseEntity<String> sendEmailAndCode(@RequestParam("email") String email, @RequestParam("code") String code){  
        if (emailService.verifyEmailCode(email, code)) {  
            return ResponseEntity.ok("success");  
        }  
        return ResponseEntity.notFound().build();  
    }
    
}
