package com.omakase.omastay.service;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.enumurate.IcStatus;
import com.omakase.omastay.mapper.CouponMapper;
import com.omakase.omastay.repository.CouponRepository;
import com.omakase.omastay.repository.IssuedCouponRepository;
import com.omakase.omastay.repository.MemberRepository;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private IssuedCouponRepository issuedCouponRepository;

    @Autowired
    private MemberRepository memberRepository;

    // 모든 쿠폰 조회
    public List<CouponDTO> getAllCoupons(){
        List<Coupon> coupons = couponRepository.findAll();
        return CouponMapper.INSTANCE.toCouponDTOList(coupons);
    }
    
    // 지정 발행 형식의 쿠폰 발행
    public int issueDesignatedCoupon(CouponDTO cDto, String selectGrade){
        int cnt=0;

        Coupon cp = CouponMapper.INSTANCE.toCoupon(cDto);

        int id = couponRepository.save(cp).getId();

        if(id > 0){ // 쿠폰 저장 성공

            List<Member> members;
            if(selectGrade.equals("all")){
                members = memberRepository.findAll();
            }else{
                members = memberRepository.findByGrade(selectGrade);
            }

            for(Member m : members){
                IssuedCoupon ic = new IssuedCoupon();
                ic.setMember(m);
                ic.setCoupon(cp);
                ic.setIcStatus(IcStatus.UNUSED);
                
                issuedCouponRepository.save(ic);
                cnt++;
            }

        }

        return cnt;
    }

    // 일회성 쿠폰 코드 형식의 쿠폰 발행
    public int issueSingleUseCoupon(CouponDTO cDto, Integer count){
        int cnt=0;

        Coupon cp = CouponMapper.INSTANCE.toCoupon(cDto);

        int id = couponRepository.save(cp).getId();

        if(id > 0){ // 쿠폰 저장 성공

            while(cnt<count){ //count 만큼 쿠폰 발행

                String randomCode = RandomStringUtils.randomAlphanumeric(8);

                while(issuedCouponRepository.existsByIcCode(randomCode)){ // randomCode 중복성 검사
                    randomCode = RandomStringUtils.randomAlphanumeric(8);
                }
    
                IssuedCoupon ic = new IssuedCoupon();
                ic.setMember(null);
                ic.setCoupon(cp);
                ic.setIcStatus(IcStatus.UNUSED);
                ic.setIcCode(randomCode);
                
                issuedCouponRepository.save(ic);
                cnt++;
            }
           
        }
        return cnt;
    }

    //다회성 쿠폰 코드 형식의 쿠폰 발행
    public int issueMultiUseCoupon(CouponDTO cDto, String code, Integer count){

        int cnt=0;

        Coupon cp = CouponMapper.INSTANCE.toCoupon(cDto);

        int id = couponRepository.save(cp).getId();

        if(id > 0){ // 쿠폰 저장 성공

            while(cnt<count){ //count 만큼 쿠폰 발행

                IssuedCoupon ic = new IssuedCoupon();
                ic.setMember(null);
                ic.setCoupon(cp);
                ic.setIcStatus(IcStatus.UNUSED);
                ic.setIcCode(code);
                
                issuedCouponRepository.save(ic);
                cnt++;
            }
           
        }
        return cnt;
    }

}
