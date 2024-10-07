package com.omakase.omastay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.custom.MemberInfoDTO;
import com.omakase.omastay.dto.custom.MemberPointDTO;
import com.omakase.omastay.dto.custom.MemberReservationDTO;
import com.omakase.omastay.dto.custom.ReviewMemberDTO;
import com.omakase.omastay.dto.custom.CouponIssuedCouponDTO;
import com.omakase.omastay.dto.custom.MemberCouponDTO;
import com.omakase.omastay.service.MyPageService;
import java.util.stream.Collectors;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import io.jsonwebtoken.lang.Collections;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    private MyPageService myPageService;
    
    @PostMapping("/member-ship")
    @ResponseBody
    public ResponseEntity<MemberReservationDTO> getMemberReservationInfo(@RequestBody Map<String, String> requestData) {
        String memberId = requestData.get("userId");
        System.out.println("프론트엔드로부터 받은 사용자 ID: " + memberId);
        try {
            Integer memberIdInt = Integer.parseInt(memberId);
            // userId로 DB에서 사용자 정보 조회
            MemberReservationDTO memberInfo = myPageService.getMemberReservationInfo(memberIdInt);
            if (memberInfo == null) {
                System.out.println("제공된 ID에 해당하는 사용자를 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // 사용자 정보가 없을 경우 404 응답
            }
            // 사용자 정보 반환
            return ResponseEntity.ok(memberInfo);
        } catch (RuntimeException e) {
            System.out.println("사용자 정보 조회 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/member-ship")
    public String showMemberShipPage(Model model) {
        // 기존의 불필요한 필드 제거하고, 새로운 생성자에 맞게 호출
        model.addAttribute("memberInfo", new MemberReservationDTO(null, "", "", "방랑자 등급", "/image/user/normal.svg"));
        return "mypage/user-member-ship"; // Thymeleaf 템플릿 반환
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
    
     
    @GetMapping("/info")
    public String userMyPageInfo(Model model) {
        // MemberInfoDTO로 데이터 생성
        model.addAttribute("memberInfo", new MemberInfoDTO(
                1,                    // ID
                "test@example.com",    // 이메일
                1000L,                // exp
                "John Doe",           // 이름
                "some-sub-value"      // sub
        ));
        return "mypage/user-mypage-info"; // Thymeleaf 템플릿 반환
    }
    

    @PostMapping("/info/userData")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getUserData(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        System.out.println("메일 받기용: " + email);
        try {
            // 기존 회원 정보, 등급, 이미지 관련 데이터 가져오기
            Map<String, Object> responseData = myPageService.getMemberDataWithGradeAndImage(email);
            Map<String, String> emailReceiveStatusData = myPageService.getEmailReceiveStatus(email);
            responseData.put("emailReceiveStatus", emailReceiveStatusData.get("emailReceiveStatus"));
            return ResponseEntity.ok(responseData);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/member-info")
    @ResponseBody
    public ResponseEntity<MemberDTO> getMemberInfo(@RequestBody Map<String, String> requestData) {
        String memberId = requestData.get("userId");
        System.out.println("프론트엔드로부터 받은 사용자 ID: " + memberId);
        try {
            Integer memberIdInt = Integer.parseInt(memberId);
            MemberDTO memberInfo = myPageService.getMemberInfo(memberIdInt);
            return ResponseEntity.ok(memberInfo);
        } catch (RuntimeException e) {
            System.out.println("사용자 정보 조회 중 오류 발생: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/check-password")
    @ResponseBody
    public ResponseEntity<Boolean> checkPassword(@RequestBody Map<String, String> requestData) {
        String email = requestData.get("email");
        String inputPassword = requestData.get("password");

        try {
            boolean isMatch = myPageService.checkPassword(email, inputPassword);
            return ResponseEntity.ok(isMatch);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    // 회원 정보 수정 
    @PostMapping("/update-member-info")
    @ResponseBody
    public ResponseEntity<String> updateMemberInfo(@RequestBody MemberDTO memberDTO) {
        String email = memberDTO.getMemberProfile().getEmail();  // MemberDTO에서 이메일을 추출

        try {
            // 이메일을 통해 회원 정보를 수정
            myPageService.updateMemberInfoByEmail(email, memberDTO);
            return ResponseEntity.ok("수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정에 실패했습니다.");
        }
    }
    //회원 탈퇴 mem_status==False =1로 하기
    @PostMapping("/quit-member-info")
    @ResponseBody
    public ResponseEntity<String> quitMember(@RequestBody Map<String, String> requestData, HttpServletRequest request, HttpServletResponse response) {
        String email = requestData.get("email");

        try {
            // 서비스 호출하여 회원 탈퇴 처리
            myPageService.quitMember(email);
            HttpSession session = request.getSession();
            session.invalidate(); // 세션 무효화

            // 쿠키에서 accessToken 및 refreshToken 제거
            Cookie accessTokenCookie = new Cookie("accessToken", null);
            accessTokenCookie.setMaxAge(0);
            accessTokenCookie.setPath("/");
            response.addCookie(accessTokenCookie);

            Cookie refreshTokenCookie = new Cookie("refreshToken", null);
            refreshTokenCookie.setMaxAge(0);
            refreshTokenCookie.setPath("/");
            response.addCookie(refreshTokenCookie);
            return ResponseEntity.ok("회원 탈퇴가 성공적으로 처리되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원 탈퇴 처리 중 오류가 발생했습니다: " + e.getMessage());
        }
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

    @PostMapping("/point")
    @ResponseBody
    public ResponseEntity<MemberPointDTO> getUserPoints(@RequestBody Map<String, Integer> requestData) {
        Integer memberId = requestData.get("memberId");

        System.out.println("정보추가");
        MemberPointDTO memberPointDTO = myPageService.getMemberPoints(memberId);

        if (memberPointDTO == null) {
            memberPointDTO = new MemberPointDTO();
        }

        if (memberPointDTO.getFormattedPoints() == null) {
            memberPointDTO.setFormattedPoints(Collections.emptyList());
        }

        // 로그 출력
        System.out.println("DTO 테스트에여: " + memberPointDTO);

        // JSON 형식으로 응답
        return ResponseEntity.ok(memberPointDTO);
    }


    @PostMapping("/coupon")
    public ResponseEntity<MemberCouponDTO> getUserCoupons(@RequestBody Map<String, Integer> requestData) {
        Integer memberId = requestData.get("memberId");
        System.out.println("컨트롤러 테스트1"+memberId);
        // memberId가 없을 경우 잘못된 요청 처리
        if (memberId == null) {
            return ResponseEntity.badRequest().build();
        }

        // 서비스에서 쿠폰 정보를 가져옴
        MemberCouponDTO memberCouponDTO = myPageService.getCouponsForMember(memberId);
        System.out.println("컨트롤러 테스트2"+memberCouponDTO);
        // 쿠폰 정보를 반환
        return ResponseEntity.ok(memberCouponDTO);
    }

    @PostMapping("/register-coupon")
    public ResponseEntity<String> registerCoupon(@RequestBody Map<String, String> requestData) {
        String icCode = requestData.get("couponCode");
        Integer memberId = Integer.parseInt(requestData.get("memberId"));

        // 쿠폰 등록 서비스 호출
        String resultMessage = myPageService.registerCoupon(icCode, memberId);

        return ResponseEntity.ok(resultMessage);
    }

    @PostMapping("/review")
    @ResponseBody
    public List<ReviewMemberDTO> reviewMember(@RequestBody Map<String, Integer> requestData) {
        int memIdx = requestData.get("memIdx");
        // 서비스 호출 후 결과 반환
        return myPageService.reviewMember(memIdx);
    }
    
    @PostMapping("/delete-reviews")
    public Map<String, String> deleteReviews(@RequestBody Map<String, List<Integer>> requestData) {
        List<Integer> reviewIds = requestData.get("reviewIds");
        boolean success = myPageService.deleteReviews(reviewIds);
        
        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("message", "리뷰가 성공적으로 삭제되었습니다.");
        } else {
            response.put("message", "리뷰 삭제 중 오류가 발생했습니다.");
        }
        return response;
    }
    
}
