package com.omakase.omastay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.MemberInfoDTO;
import com.omakase.omastay.dto.custom.ReservationWithImage;
import com.omakase.omastay.service.MyPageService;
import com.omakase.omastay.service.RoomInfoService;



@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private RoomInfoService roomInfoService;


    @Value("${upload}")
    private String realPath;

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
    public ModelAndView reservation() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("mypage/user-reservation");
        return mv;
    }

    @PostMapping("/reservation")
    @ResponseBody
    public Map<String, Object> reservationProc(@RequestBody MemberInfoDTO memberInfo) {
        System.out.println("멤버정보" + memberInfo);
        Map<String, Object> map = new HashMap<>();
        List<ReservationDTO> reservation = myPageService.getReservationInfo(memberInfo.getId());

        List<ReservationWithImage> reservationsWithImages = new ArrayList<>();

        for (ReservationDTO res : reservation) {
            RoomInfoDTO room = roomInfoService.getRoomInfo(res.getRoomIdx());
            System.out.println("호텔정보" + room.getHIdx());
            ImageDTO image = roomInfoService.getImage(room.getHIdx());
            String img = realPath + "host/" + image.getImgName().getFName();

            HostInfoDTO host = roomInfoService.getHostInfo(room.getHIdx());

            ReservationWithImage reservationWithImage = new ReservationWithImage();
            reservationWithImage.setReservation(res);
            reservationWithImage.setImage(img);
            reservationWithImage.setHostName(host.getHname());
            reservationsWithImages.add(reservationWithImage);
        }
        map.put("reservation", reservationsWithImages);

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
        Map<String, Object> map = new HashMap<String, Object>();
        ReservationDTO res = myPageService.getReservation(reservation.getId());
        System.out.println("예약정보" + res);
        PaymentDTO payment = myPageService.getPayment(res.getPayIdx());
        System.out.println("결제정보" + payment);
        RoomInfoDTO room = roomInfoService.getRoomInfo(res.getRoomIdx());
        ImageDTO image = roomInfoService.getImage(room.getHIdx());
        String img = realPath + "host/" + image.getImgName().getFName();
        HostInfoDTO host = roomInfoService.getHostInfo(room.getHIdx());
        map.put("room", room);
        map.put("image", img);
        map.put("host", host);
        map.put("reservation", res);
        map.put("payment", payment);
        return map;
    }
}
