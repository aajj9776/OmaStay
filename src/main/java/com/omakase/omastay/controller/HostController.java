package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.service.AdminMemberService;
import com.omakase.omastay.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/host")
@RequiredArgsConstructor  
public class HostController {

    @Autowired
    private AdminMemberService adminMemberService;
    
    private final EmailService emailService;

    @Autowired
    private HttpSession session;

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

    @RequestMapping("/hostregist")
    public ResponseEntity<String> hostregister(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email) {
        boolean result = adminMemberService.hostregist(id, pw, email);
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(500).body("Registration failed");
        }
    }

    @RequestMapping("/hostlogin")
    public ModelAndView hostlogin(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        AdminMemberDTO adminMemberDTO = adminMemberService.hostlogin(id,pw);

        ModelAndView mv = new ModelAndView();
        if (adminMemberDTO != null) {
            session.setAttribute("adminMember", adminMemberDTO); // 세션에 사용자 정보 저장
            mv.addObject("adminMember", adminMemberDTO);
            mv.setViewName("host/host_main");
        } else {
            mv.addObject("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            mv.setViewName("host/host_login");
        }

        return mv;
    }

    @RequestMapping("/idemailsend")  
    public ResponseEntity<String> sendIdEmail(@RequestParam("id") String id, @RequestParam("email") String email) throws MessagingException { 
        boolean isValid = adminMemberService.hostfindpw(id, email);

        if (isValid) {
            emailService.sendEmail(email);
            return ResponseEntity.ok("success");
        } else {
            String errorMessage = "아이디 또는 이메일이 일치하지 않습니다.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }  

    @RequestMapping("/changepw")
    public ResponseEntity<String> hostchangepw(@RequestParam("id") String id, @RequestParam("pw") String pw, @RequestParam("email") String email) {
        boolean result = adminMemberService.hostchagepw(id, email, pw);
        if (result) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(500).body("Change failed");
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "host/host_login"; 
    }
    
}
