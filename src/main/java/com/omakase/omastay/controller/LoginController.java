package com.omakase.omastay.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.service.MemberService;
import com.omakase.omastay.vo.AddressVo;
import com.omakase.omastay.vo.UserProfileVo;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
public class LoginController {
     @Autowired
    private MemberService memberService;

    @GetMapping()
    public String login() {
        return "login/login";
    }

    @RequestMapping("/user")
    public String signup() {
        return "login/user_login";
    }

    @RequestMapping("/user/register")
    public String register() {
        return "login/user_register";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "login/admin_login";
    }

    @RequestMapping("/userfindpw")
    public String findpw() {
        return "login/userfindpw";
    }

    @RequestMapping("/userchangepw")
    public String userchangepw() {
        return "login/userchangepw";
    }


    @GetMapping("/social/naver")
    public void naverLogin(HttpServletResponse response) throws IOException {
        String naverLoginUrl = memberService.getNaverLoginUrl();  // 서비스에서 네이버 로그인 URL을 받아옴
        response.sendRedirect(naverLoginUrl);  // 네이버 로그인 페이지로 리다이렉트
    }


     @GetMapping("/naver/callback")
     public String naverCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
        // 네이버에서 받은 code와 state 값을 사용해 access_token 요청  향후 웹에 적용 밑 DB에 넣기
        String accessToken = memberService.handleNaverCallback(code, state);
    
        // 로그인 성공 후 메인 페이지로 리다이렉트
        return "redirect:/";  // 메인 페이지로 리다이렉트
    }



    @RequestMapping("/social/google")
    public String googleLogin() {
        return "login/social/google"; // 소셜 로그인 
    }
    
    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> registerMember(@RequestBody Map<String, String> requestData) {
        String name= requestData.get("name");
        String email = requestData.get("email");
        String password = requestData.get("password");
        String birth = requestData.get("birth");
        String gender = requestData.get("gender");
        String emailSubscription = requestData.get("emailSubscription");
        String phone = requestData.get("phone");
        String postCode = requestData.get("postCode");
        String street = requestData.get("street");
        String detail = requestData.get("detail");

        // MemberDTO 생성 및 서비스 호출
        MemberDTO memberDTO = new MemberDTO();

        // **memberProfile null 체크 후 설정**
        if (memberDTO.getMemberProfile() == null) {
            memberDTO.setMemberProfile(new UserProfileVo());
        }
        memberDTO.setMemName(name);
        memberDTO.getMemberProfile().setEmail(email);
        memberDTO.getMemberProfile().setPw(password);
        memberDTO.setMemPhone(phone);
        memberDTO.setMemBirth(birth);

        // **addressVo null 체크 후 설정**
        if (memberDTO.getAddressVo() == null) {
            memberDTO.setAddressVo(new AddressVo());
        }

        // 주소 정보 설정
        memberDTO.getAddressVo().setPostCode(postCode);
        memberDTO.getAddressVo().setStreet(street);
        memberDTO.getAddressVo().setDetail(detail);

        // 서비스 호출하여 데이터 저장
        memberService.registerMember(memberDTO, gender, emailSubscription);

        // 성공 응답 리턴
       // 성공 응답을 JSON 형식으로 반환
       Map<String, Object> response = new HashMap<>();
       response.put("message", "회원가입 성공");
   
       return ResponseEntity.ok(response); // JSON 응답
    }
}
