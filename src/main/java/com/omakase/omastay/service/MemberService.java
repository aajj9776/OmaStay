package com.omakase.omastay.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.entity.Grade;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.Gender;
import com.omakase.omastay.entity.enumurate.Social;
import com.omakase.omastay.repository.GradeRepository;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.repository.ReservationRepository;
import com.omakase.omastay.vo.UserProfileVo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private GradeRepository gradeRepository;

    private final String clientId = "2iV7rMvgZbmW51NsmXrs";  // 네이버 클라이언트 ID
    private final String clientSecret = "4w8zFgGlCG";  // 네이버 클라이언트 시크릿
    private final String redirectUri = "http://localhost:9090/login/naver/callback";  // 네이버 콜백 URL

    // 네이버 로그인 구현 범위 시작
    public String getNaverLoginUrl() throws UnsupportedEncodingException {
        String encodedRedirectUri = URLEncoder.encode(redirectUri, "UTF-8");
        String state = "random_state";  // 보안을 위해 랜덤 상태 값 생성
        String apiUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" 
                        + clientId + "&redirect_uri=" + encodedRedirectUri + "&state=" + state;
        return apiUrl;
    }

    // 네이버 콜백 처리 + access_token을 추출
    public String handleNaverCallback(String code, String state) {
        String tokenUrl = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&client_id="
                        + clientId + "&client_secret=" + clientSecret + "&code=" + code + "&state=" + state;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(tokenUrl, String.class);

        // JSON 으로 access_token 추출
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            String accessToken = jsonNode.get("access_token").asText();
            
            System.out.println("네이버 로그인 성공, access_token: " + accessToken);
           
            //엑세스 토큰이 나오는지 확인 확인하면 바로 제거 현재 출력은 확인

            // access_token을 사용(향후 로그인 후 토큰 사용)
            return accessToken;

        } catch (Exception e) {
           
            e.printStackTrace();
            return null;
        }
    }
    //네이버 로그인 구현 범위 끝


    @Transactional
    public void registerMember(MemberDTO memberDTO, String genderString, String emailSubscription) {
        // 회원 가입 시 기본 등급을 가져옵니다.
        Grade grade = gradeRepository.findById(1)
            .orElseThrow(() -> new IllegalArgumentException("기본 등급을 찾을 수 없습니다."));

        // Gender 처리: HTML에서 선택한 값을 enum으로 변환하여 저장
        Gender gender;
        if ("male".equalsIgnoreCase(genderString)) {
            gender = Gender.MAN;
        } else if ("female".equalsIgnoreCase(genderString)) {
            gender = Gender.WOMEN;
        } else {
            throw new IllegalArgumentException("Invalid gender value: " + genderString);
        }

        // 이메일 수신 여부: yes이면 TRUE, no이면 FALSE
        BooleanStatus emailCheck = "yes".equalsIgnoreCase(emailSubscription) ? BooleanStatus.TRUE : BooleanStatus.FALSE;

        // Member 엔티티를 생성하여 입력 데이터 세팅
        Member member = new Member();
        member.setGrade(grade);  // 기본 등급
        member.setMemberProfile(memberDTO.getMemberProfile());  // 이메일, 비밀번호 등
        member.setMemName(memberDTO.getMemName());
        member.setMemPhone(memberDTO.getMemPhone());
        member.setMemEmailCheck(emailCheck);
        member.setMemName(memberDTO.getMemName());
        member.setMemBirth(memberDTO.getMemBirth());
        member.setMemJoinDate(LocalDateTime.now());
        member.setMemSocial(Social.NONE);  // 소셜 로그인 사용 안 함 (NONE)
        member.setAddressVo(memberDTO.getAddressVo());  // 주소 정보
        member.setMemGender(gender);  // 성별 저장

        member.setMemberProfile(memberDTO.getMemberProfile());
        member.getMemberProfile().setStatus(BooleanStatus.TRUE);  // 가입 상태로 설정

        // 회원 정보를 DB에 저장
        memberRepository.save(member);
    }


}
