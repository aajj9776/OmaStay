package com.omakase.omastay.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate; // java.time.LocalDate 사용
import java.time.LocalDateTime; // java.time.LocalDateTime 사용
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;


import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.custom.CouponIssuedCouponDTO;
import com.omakase.omastay.dto.custom.MemberCouponDTO;
import com.omakase.omastay.dto.custom.MemberPointDTO;
import com.omakase.omastay.dto.custom.MemberReservationDTO;
import com.omakase.omastay.dto.custom.ReviewMemberDTO;
import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Point;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.mapper.MemberMapper;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.repository.CouponRepository;
import com.omakase.omastay.repository.IssuedCouponRepository;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.repository.PaymentRepository;
import com.omakase.omastay.repository.PointRepository;
import com.omakase.omastay.repository.ReservationRepository;
import com.omakase.omastay.repository.ReviewRepository;

import java.time.LocalDateTime;  // 이 부분이 java.time을 사용하는 부분입니다.

import jakarta.persistence.EntityNotFoundException;


@Service
public class MyPageService {


    @Value("${upload}")  // yml 파일의 경로 주입
    private String uploadPath;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IssuedCouponRepository issuedCouponRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CouponRepository couponRepository;

    public MemberDTO getMemberInfo(int memberId) {

        Optional<Member> res = memberRepository.findMemberWithReservations(memberId);

        MemberDTO memberDTO = MemberMapper.INSTANCE.toMemberDTO(res.get());

        return memberDTO;
    }

    public PaymentDTO getPaymentForReservation(Integer id) {
        //  // Reservation을 조회하면서 Payment를 함께 가져오는 로직
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + id));

        // Reservation을 통해 Payment를 조회
        Payment payment = reservation.getPayment();

        return PaymentMapper.INSTANCE.toPaymentDTO(payment);

    }

    //멤버쉽 조회 기능 시작
    // 멤버쉽 정보 조회 서비스 메서드
    public MemberReservationDTO getMemberReservationInfo(Integer memIdx) {
        Optional<Member> memberOptional = memberRepository.findById(memIdx);
        if (!memberOptional.isPresent()) {
            throw new RuntimeException("Member 테이블에 내용이 없습니다.");
        }

        Member member = memberOptional.get();
        Integer memberId = member.getId();
        String memberName = member.getMemName();
        String memberEmail = member.getMemberProfile().getEmail();

        // g_idx 값 직접 조회
        Integer gIdx = memberRepository.findGIdxByMemberId(memberId);
        String grade = getGradeByGidx(gIdx);  // g_idx에 따른 등급 설정
        String imageUrl = getImageUrlByGidx(gIdx);  // g_idx에 따른 이미지 설정

        // DTO 반환
        return new MemberReservationDTO(memberId, memberName, memberEmail, grade, imageUrl);
    }

    // g_idx에 따른 등급 설정 메서드
    private String getGradeByGidx(int gIdx) {
        switch (gIdx) {
            case 1: return "방랑자 등급";
            case 2: return "여행가 등급";
            case 3: return "개척자 등급";
            default: return "알 수 없는 등급";
        }
    }

    // g_idx에 따른 이미지 URL 설정 메서드
    private String getImageUrlByGidx(int gIdx) {
        switch (gIdx) {
            case 1: return "/image/user/normal.svg";
            case 2: return "/image/user/plusmember.svg";
            case 3: return "/image/user/VIP.svg";
            default: return "/image/user/default.svg";
        }
    }
    
    public MyPageService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public MemberDTO getUserInfoByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
        return new MemberDTO(member);
    }

    // 회원 + 등급 + 이미지 정보 조회
    public Map<String, Object> getMemberDataWithGradeAndImage(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
    
        // g_idx 값 조회
        Integer gIdx = memberRepository.findGIdxByMemberId(member.getId());
    
        // g_idx에 따른 등급과 이미지 설정
        String grade = getGradeByGidx(gIdx);
        String imageUrl = getImageUrlByGidx(gIdx);
    
        // 데이터 구성
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("memberDTO", new MemberDTO(member));  // 기존의 memberDTO
        responseData.put("grade", grade);                      // 등급 정보
        responseData.put("imageUrl", imageUrl);                // 이미지 URL 정보
    
        return responseData;
    }
        


    // 비밀번호 검증 메서드
    public boolean checkPassword(String email, String inputPassword) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalArgumentException("해당 이메일의 사용자가 없습니다.");
        }

        // 입력된 비밀번호를 DB에 저장된 암호화된 비밀번호와 비교
        return passwordEncoder.matches(inputPassword, member.getMemberProfile().getPw());
    }

    public void updateMemberInfoByEmail(String email, MemberDTO memberDTO) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalArgumentException("해당 이메일의 사용자가 없습니다.");
        }

        // DTO 값으로 기존 회원 정보 업데이트
        member.setMemPhone(memberDTO.getMemPhone());
        member.setMemName(memberDTO.getMemName());
        member.setMemBirth(memberDTO.getMemBirth());
        member.setAddressVo(memberDTO.getAddressVo());
        member.setMemEmailCheck(memberDTO.getMemEmailCheck());

        // 업데이트된 회원 정보를 저장
        memberRepository.save(member);
    }

     public Map<String, String> getEmailReceiveStatus(String email) {
        Member member = memberRepository.findByEmail(email);
        // 이메일 수신 여부를 확인하여 결과 반환
        Map<String, String> response = new HashMap<>();
        if (member.getMemEmailCheck() == BooleanStatus.TRUE) {
            response.put("emailReceiveStatus", "YES");
        } else {
            response.put("emailReceiveStatus", "NO");
        }
        System.out.println("서비스 확인" +response);
        return response;
    }

    public void quitMember(String email) {
        // 이메일을 통해 회원 정보 조회
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalArgumentException("해당 이메일로 등록된 회원이 없습니다.");
        }

        // mem_status 값을 1로 변경하여 탈퇴 처리
        member.getMemberProfile().setStatus(BooleanStatus.FALSE);  // mem_status == 1 (FALSE)
        memberRepository.save(member);
    }


    //해당 유저 포인트 조회
    public MemberPointDTO getMemberPoints(int memberId) {
        // 회원 정보를 가져옴
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
            System.out.println("멤버 테스틑"+member);
        // 포인트 데이터를 가져오는 로직
        List<Point> points = pointRepository.findPointsByMemberId(memberId);
        if (points == null) {
            points = new ArrayList<>(); // null일 경우 빈 리스트로 초기화
        }

        System.out.println("포인트 테스트"+points);
        // PointDTO 리스트로 변환
        List<PointDTO> pointDTOList = points.stream()
                .map(PointDTO::new)
                .collect(Collectors.toList());

        // MemberDTO 생성 (필요한 경우 로직을 추가하여 MemberDTO 변환)
        MemberDTO memberDTO = new MemberDTO(member);

        // MemberPointDTO 생성 (포맷된 포인트 리스트와 총합 계산)
        MemberPointDTO memberPointDTO = new MemberPointDTO(memberDTO, pointDTOList);

        return memberPointDTO;
    }
    // mem_idx로 쿠폰을 조회하고 DTO로 변환하여 반환하는 메서드

    @Transactional
    public MemberCouponDTO getCouponsForMember(int memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    
        List<IssuedCoupon> issuedCoupons = issuedCouponRepository.findByMemIdx(memberId);
    
        // 현재 시간을 가져옴
        LocalDateTime now = LocalDateTime.now();
    
        List<CouponIssuedCouponDTO> couponDTOList = issuedCoupons.stream()
                .filter(issuedCoupon -> issuedCoupon.getCoupon().getCpStartEnd().getEnd().isAfter(now)) // 만료일이 현재 시간보다 이후인 경우만 포함
                .map(issuedCoupon -> {
                    CouponIssuedCouponDTO dto = new CouponIssuedCouponDTO();
                    dto.setCouponId(issuedCoupon.getCoupon().getId());
                    dto.setCouponContent(issuedCoupon.getCoupon().getCpContent());
                    dto.setCouponSale(issuedCoupon.getCoupon().getCpSale());
    
                    // LocalDateTime을 바로 설정
                    dto.setCouponStarttime(issuedCoupon.getCoupon().getCpStartEnd().getStart());
                    dto.setCouponEndtime(issuedCoupon.getCoupon().getCpStartEnd().getEnd());
    
                    dto.setIcStatus(issuedCoupon.getIcStatus().name());
                    dto.setIcCode(issuedCoupon.getIcCode());
                    return dto;
                })
                .collect(Collectors.toList());
    
        MemberDTO memberDTO = new MemberDTO(member);
        return new MemberCouponDTO(memberDTO, couponDTOList);
    }
    // 쿠폰 등록
    @Transactional
    public String registerCoupon(String icCode, int memberId) {
        // 쿠폰 코드로 해당 쿠폰을 조회하고 mem_idx가 null인 경우만 처리
        Optional<IssuedCoupon> issuedCouponOpt = issuedCouponRepository.findByIcCodeAndMemIdxIsNull(icCode);

        if (!issuedCouponOpt.isPresent()) {
            return "해당 쿠폰이 없거나 이미 사용되었습니다.";
        }

        IssuedCoupon issuedCoupon = issuedCouponOpt.get();

        // 회원 정보를 가져옴
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        // 쿠폰에 회원 정보 할당 (mem_idx 설정)
        issuedCoupon.setMember(member);
        issuedCouponRepository.save(issuedCoupon);

        return "쿠폰이 성공적으로 등록되었습니다.";
    }

    // 쿠폰 추가
    @Transactional
    public void registerCouponForMember(String couponCode, int memberId) {
        // 해당 쿠폰 코드를 가진 쿠폰이 존재하는지 확인
        IssuedCoupon issuedCoupon = issuedCouponRepository.findByIcCode(couponCode)
                .orElseThrow(() -> new RuntimeException("해당 쿠폰 코드를 찾을 수 없습니다."));

        // 쿠폰이 이미 다른 회원에게 사용되었는지 확인
        if (issuedCoupon.getMember() != null) {
            throw new RuntimeException("해당 쿠폰은 이미 사용되었습니다.");
        }

        // 회원 정보 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원을 찾을 수 없습니다."));

        // 쿠폰을 회원에게 할당
        issuedCoupon.setMember(member);
        issuedCouponRepository.save(issuedCoupon);
    }

    // 리뷰 데이터를 가져와 DTO로 변환하는 메서드
    public List<ReviewMemberDTO> reviewMember(int memIdx) {
        List<Review> reviews = reviewRepository.findReviewsWithRoomAndHotelByMemberId(memIdx);

        // Review 엔티티를 ReviewMemberDTO로 변환
        return reviews.stream().map(review -> {
            ReviewMemberDTO dto = new ReviewMemberDTO();
            dto.setId(review.getId());
            dto.setRevWriter(review.getRevWriter());
            dto.setRevContent(review.getRevContent());
            dto.setRevDate(review.getRevDate());
            dto.setRevRating(review.getRevRating());

            // 호텔명과 객실명 설정
            if (review.getReservation() != null && review.getReservation().getRoomInfo() != null) {
                dto.setHotelName(review.getReservation().getRoomInfo().getHostInfo().getHname());
                dto.setRoomName(review.getReservation().getRoomInfo().getRoomName());
            }

            // 이미지 URL 설정
            if (review.getRevFileImageNameVo() != null) {
                String fname = review.getRevFileImageNameVo().getFName();
                if (fname != null && !fname.isEmpty()) {
                    String[] fileNames = fname.split(",");
                    List<String> imageUrls = Arrays.stream(fileNames)
                        .map(fileName -> uploadPath + "review/" + fileName)
                        .collect(Collectors.toList());
                    dto.setImageUrls(imageUrls);
                } else {
                    dto.setImageUrls(Collections.emptyList());
                }
            } else {
                dto.setImageUrls(Collections.emptyList());
            }

            return dto;
        }).collect(Collectors.toList());
    }

        // 리뷰 삭제 
        public boolean deleteReviews(List<Integer> reviewIds) {
            try {
                for (Integer reviewId : reviewIds) {
                    Review review = reviewRepository.findById(reviewId).orElse(null);
                    if (review != null) {
                        review.setRevStatus(BooleanStatus.FALSE); // 리뷰 상태를 삭제로 변경 (BooleanStatus 사용)
                        reviewRepository.save(review);
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
}
