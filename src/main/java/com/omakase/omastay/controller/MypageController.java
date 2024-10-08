package com.omakase.omastay.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView reservation(
        @RequestParam("id") Integer id,
        @PageableDefault(size = 3, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("회원번호 " + id);
        ModelAndView mv = new ModelAndView();
        boolean newTrip = false;

        Page<ReservationDTO> reservationPage = myPageService.getNewReservationInfo(id, pageable);

        int nowPage = reservationPage.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(startPage + 4, reservationPage.getTotalPages());

        List<ReservationDTO> reservation = reservationPage.getContent();
        System.out.println("리스트사이즈" +reservation.size());
        if( reservation != null && reservation.size() > 0){
            newTrip = true;
        }
        List<ReservationWithImage> reservationsWithImages = new ArrayList<>();

        for (ReservationDTO res : reservation) {
            RoomInfoDTO room = roomInfoService.getRoomInfo(res.getRoomIdx());
            System.out.println("호텔정보" + room.getHIdx());
            ImageDTO image = roomInfoService.getImage(room.getHIdx());
            System.out.println("이미지" + image);
            String img = realPath + "host/" + image.getImgName().getFName();

            HostInfoDTO host = roomInfoService.getHostInfo(room.getHIdx());
            LocalDateTime start = res.getStartEndVo().getStart();
            LocalDateTime end = res.getStartEndVo().getEnd();

            LocalDate startDate = start.toLocalDate();
            LocalDate endDate = end.toLocalDate();
            
            // 날짜 차이 계산 (며칠 차이인지 계산)
            long totalNights = ChronoUnit.DAYS.between(startDate, endDate);

            ReservationWithImage reservationWithImage = new ReservationWithImage();
            reservationWithImage.setReservation(res);
            reservationWithImage.setImage(img);
            reservationWithImage.setHostName(host.getHname());
            reservationWithImage.setStart(startDate);
            reservationWithImage.setEnd(endDate);
            reservationWithImage.setDate(totalNights);
            reservationsWithImages.add(reservationWithImage);
        }
        mv.addObject("reservation", reservationsWithImages);
        mv.addObject("newTrip", newTrip);
        mv.addObject("reservationPage", reservationPage);
        mv.addObject("nowPage", nowPage);
        mv.addObject("startPage", startPage);
        mv.addObject("endPage", endPage);
        mv.addObject("id", id);

        mv.setViewName("mypage/user-reservation");
        return mv;
    }

    @PostMapping("/reservation")
    @ResponseBody
    public Map<String, Object> reservationProc(
        @RequestBody MemberInfoDTO memberInfo,
        @PageableDefault(size = 3, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("멤버정보" + memberInfo);
        Map<String, Object> map = new HashMap<>();
        Page<ReservationDTO> reservation = myPageService.getReservationInfo(memberInfo.getId(), pageable);

        int nowPage = reservation.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(startPage + 4, reservation.getTotalPages());

        List<ReservationWithImage> reservationsWithImages = new ArrayList<>();

        for (ReservationDTO res : reservation) {
            RoomInfoDTO room = roomInfoService.getRoomInfo(res.getRoomIdx());
            System.out.println("호텔정보" + room.getHIdx());
            ImageDTO image = roomInfoService.getImage(room.getHIdx());
            System.out.println("이미지" + image);
            String img = realPath + "host/" + image.getImgName().getFName();

            HostInfoDTO host = roomInfoService.getHostInfo(room.getHIdx());

            ReservationWithImage reservationWithImage = new ReservationWithImage();
            reservationWithImage.setReservation(res);
            reservationWithImage.setImage(img);
            reservationWithImage.setHostName(host.getHname());
            reservationsWithImages.add(reservationWithImage);
        }
        map.put("reservation", reservationsWithImages);
        map.put("nowPage", nowPage);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        map.put("id", memberInfo.getId());

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
        System.out.println("하이");
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

    @GetMapping("/cancel_content")
    public String checkDetail() {
        return "mypage/modal/cancel_content";
    }


    @PostMapping("/cancel_content")
    @ResponseBody
    public Map<String, Object> checkDetail(@RequestParam("id") Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        ReservationDTO res = myPageService.getReservation(id);
        PaymentDTO payment = myPageService.getPayment(res.getPayIdx());
        map.put("payment", payment);
        return map;
    }


    @GetMapping("/noCancel_content")
    public String noCancel() {
        return "reservation/modal/noCancel_content";
    }


    @PostMapping("/noCancel_content")
    @ResponseBody
    public Map<String, Object> noCancel(@RequestParam("id") Integer id) {
        System.out.println("들어와야해요" + id);
        Map<String, Object> map = new HashMap<String, Object>();
        PaymentDTO payment = myPageService.getPayment(id);
        map.put("payment", payment);
        return map;
    }


    //이거 3개는 모달창
    @RequestMapping("/room_info")
    public String roomInfo() {
        return "mypage/modal/room_info";
    }
}
