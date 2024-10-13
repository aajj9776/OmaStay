package com.omakase.omastay.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.NonMemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.NoReserverDTO;
import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.service.EmailService;
import com.omakase.omastay.service.ImageService;
import com.omakase.omastay.service.MyPageService;
import com.omakase.omastay.service.NonMemberService;
import com.omakase.omastay.service.ReservationService;
import com.omakase.omastay.service.RoomInfoService;
import com.omakase.omastay.vo.StartEndVo;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private NonMemberService nonMemberService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoomInfoService roomInfoService;

    @Autowired ImageService imageService;

    @Autowired
    private MyPageService myPageService;

    @Value("${upload}")
    private String realPath;

    @GetMapping
    public ModelAndView reservation(@RequestParam("room") Integer ridx) {
        ModelAndView mv = new ModelAndView();

        List<ImageDTO> dto = imageService.getImages(ridx);

        RoomInfoDTO room = roomInfoService.getRoomInfo(ridx);

        String fname = dto.get(0).getImgName().getFName();
        String path = realPath + "room/" + fname;
        System.out.println(path);
            
        mv.addObject("room", room.getRoomName());
        mv.addObject("img", path);
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
    public String payComplete(
        PaymentDTO payment, RedirectAttributes redirectAttributes, ReservationDTO reservation, NonMemberDTO nonMember,
        @RequestParam("checkIn") String checkIn, @RequestParam("checkOut") String checkOut, @RequestParam("roomName") String roomName) {
        System.out.println("예약정보" + reservation);
        System.out.println("결제정보" + payment);
        System.out.println("체크인"+checkIn);
        
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime start = LocalDateTime.parse(checkIn, formatter);
        LocalDateTime end = LocalDateTime.parse(checkOut, formatter);
        
        // StartEndVo 객체를 설정
        StartEndVo startEndVo = new StartEndVo(start, end);
        System.out.println("날짜시작" + startEndVo.getStart());
        System.out.println("날짜끝" + startEndVo.getEnd());
        reservation.setStartEndVo(startEndVo);
        ReservationDTO check = reservationService.checkReservation(reservation);
        System.out.println("이거왜 null임?" + check);
        if(check != null ){
            return "redirect:reservation/error";
        }

        //결제정보 저장
        PaymentDTO res = reservationService.insertPaymentInfo(payment);
        System.out.println("결과" + res);
        if( res == null) {
            return "redirect:reservation/payment_fail";
        }

        //예약정보 저장
        ReservationDTO reserve = null;
        ReservationDTO noReserver = null;
        System.out.println("회원번호" + reservation.getMemIdx());
        if( res.getPayStatus() == PayStatus.PAY) {
            System.out.println("왜 안됨?");
            
            if( reservation.getMemIdx() != null && reservation.getMemIdx() > 0){
                reservation.setPayIdx(res.getId());
                reserve = reservationService.insertReservationInfo(reservation, res);
                System.out.println("회원 예약결과" + reserve);
            } else {
                System.out.println("비회원 예약 처리");
                System.out.println("비회원 파라미터 " + nonMember);
                NonMemberDTO nMember = nonMemberService.insertNonMember(nonMember);
                noReserver = reservationService.insertNonMemberReservationInfo(reservation, res, nMember);
                System.out.println("비회원 예약결과" + noReserver);
            }

        } else {
            return "redirect:reservation/payment_fail";
        }
        // 성공적인 예약 처리
        if (reserve != null && reserve.getResStatus() == ResStatus.PENDING) {
            redirectAttributes.addAttribute("orderId", reserve.getResNum());
            redirectAttributes.addAttribute("payStatus", payment.getPayStatus());
            redirectAttributes.addAttribute("amount", reserve.getResPrice());
            redirectAttributes.addAttribute("payContent", payment.getPayContent());
            redirectAttributes.addAttribute("start", start);
            redirectAttributes.addAttribute("end", end);
            redirectAttributes.addAttribute("roomName", roomName);
            return "redirect:reservation/payment_complete";
        } else if (noReserver != null && noReserver.getResStatus() == ResStatus.PENDING) {
            redirectAttributes.addAttribute("orderId", noReserver.getResNum());
            redirectAttributes.addAttribute("payStatus", payment.getPayStatus());
            redirectAttributes.addAttribute("amount", noReserver.getResPrice());
            redirectAttributes.addAttribute("payContent", payment.getPayContent());
            redirectAttributes.addAttribute("start", start);
            redirectAttributes.addAttribute("end", end);
            redirectAttributes.addAttribute("roomName", roomName);
            return "redirect:reservation/payment_complete";
        } 

        return "redirect:reservation/payment_fail";
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
        mv.setViewName("reservation/no_login");
        return mv;
    }

    @PostMapping("/noReservation")
    public ModelAndView noReservation(NoReserverDTO nonMember) {
        ModelAndView mv = new ModelAndView();
        System.out.println("nonMember" + nonMember);

        ReservationDTO reserver = reservationService.getNoReservation(nonMember.getResNum(), nonMember.getNonEmail());
        System.out.println("비회원예약 정보" + reserver);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String start = reserver.getStartEndVo().getStart().format(formatter);
        String end = reserver.getStartEndVo().getEnd().format(formatter);
        PaymentDTO pay = myPageService.getPayment(reserver.getPayIdx());
        String payDate = pay.getPayDate().format(formatter);


        NonMemberDTO member = nonMemberService.getNoMember(reserver.getNonIdx());
        RoomInfoDTO room = roomInfoService.getRoomInfo(reserver.getRoomIdx());
        ImageDTO img = roomInfoService.getImage(room.getHIdx());
        String image = realPath + "host/" + img.getImgName().getFName();
         HostInfoDTO host = roomInfoService.getHostInfo(room.getHIdx());
        System.out.println(image);

        if( reserver != null ){
            mv.setViewName("reservation/noReservation");
            mv.addObject("noReservation", reserver);
            mv.addObject("start", start);
            mv.addObject("end", end);
            mv.addObject("room", room);
            mv.addObject("image", image);
            mv.addObject("pay", pay);
            mv.addObject("payDate", payDate);
            mv.addObject("member", member);
            mv.addObject("host", host);
        } 

        return mv;
    }


}
