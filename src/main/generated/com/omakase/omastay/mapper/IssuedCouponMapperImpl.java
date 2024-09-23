package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.entity.IssuedCoupon;
import com.omakase.omastay.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T16:50:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class IssuedCouponMapperImpl implements IssuedCouponMapper {

    @Override
    public IssuedCouponDTO toIssuedCouponDTO(IssuedCoupon issuedCoupon) {
        if ( issuedCoupon == null ) {
            return null;
        }

        IssuedCouponDTO issuedCouponDTO = new IssuedCouponDTO();

        issuedCouponDTO.setMIdx( issuedCouponMemberId( issuedCoupon ) );
        issuedCouponDTO.setCpIdx( issuedCouponCouponId( issuedCoupon ) );
        issuedCouponDTO.setId( issuedCoupon.getId() );
        issuedCouponDTO.setIcStatus( issuedCoupon.getIcStatus() );
        issuedCouponDTO.setIcCode( issuedCoupon.getIcCode() );
        issuedCouponDTO.setIcNone( issuedCoupon.getIcNone() );
        issuedCouponDTO.setCoupon( couponToCouponDTO( issuedCoupon.getCoupon() ) );

        return issuedCouponDTO;
    }

    @Override
    public IssuedCoupon toIssuedCoupon(IssuedCouponDTO issuedCouponDTO) {
        if ( issuedCouponDTO == null ) {
            return null;
        }

        IssuedCoupon issuedCoupon = new IssuedCoupon();

        issuedCoupon.setMember( issuedCouponDTOToMember( issuedCouponDTO ) );
        issuedCoupon.setCoupon( issuedCouponDTOToCoupon( issuedCouponDTO ) );
        issuedCoupon.setId( issuedCouponDTO.getId() );
        issuedCoupon.setIcStatus( issuedCouponDTO.getIcStatus() );
        issuedCoupon.setIcCode( issuedCouponDTO.getIcCode() );
        issuedCoupon.setIcNone( issuedCouponDTO.getIcNone() );

        return issuedCoupon;
    }

    @Override
    public List<IssuedCouponDTO> toIssuedCouponDTOList(List<IssuedCoupon> issuedCouponList) {
        if ( issuedCouponList == null ) {
            return null;
        }

        List<IssuedCouponDTO> list = new ArrayList<IssuedCouponDTO>( issuedCouponList.size() );
        for ( IssuedCoupon issuedCoupon : issuedCouponList ) {
            list.add( toIssuedCouponDTO( issuedCoupon ) );
        }

        return list;
    }

    @Override
    public List<IssuedCoupon> toIssuedCouponList(List<IssuedCouponDTO> issuedCouponDTOList) {
        if ( issuedCouponDTOList == null ) {
            return null;
        }

        List<IssuedCoupon> list = new ArrayList<IssuedCoupon>( issuedCouponDTOList.size() );
        for ( IssuedCouponDTO issuedCouponDTO : issuedCouponDTOList ) {
            list.add( toIssuedCoupon( issuedCouponDTO ) );
        }

        return list;
    }

    private Integer issuedCouponMemberId(IssuedCoupon issuedCoupon) {
        if ( issuedCoupon == null ) {
            return null;
        }
        Member member = issuedCoupon.getMember();
        if ( member == null ) {
            return null;
        }
        Integer id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer issuedCouponCouponId(IssuedCoupon issuedCoupon) {
        if ( issuedCoupon == null ) {
            return null;
        }
        Coupon coupon = issuedCoupon.getCoupon();
        if ( coupon == null ) {
            return null;
        }
        Integer id = coupon.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected CouponDTO couponToCouponDTO(Coupon coupon) {
        if ( coupon == null ) {
            return null;
        }

        CouponDTO couponDTO = new CouponDTO();

        couponDTO.setId( coupon.getId() );
        couponDTO.setCpContent( coupon.getCpContent() );
        couponDTO.setCpStartEnd( coupon.getCpStartEnd() );
        couponDTO.setCpSale( coupon.getCpSale() );
        couponDTO.setCpCate( coupon.getCpCate() );
        couponDTO.setCpMethod( coupon.getCpMethod() );
        couponDTO.setCpNone( coupon.getCpNone() );

        return couponDTO;
    }

    protected Member issuedCouponDTOToMember(IssuedCouponDTO issuedCouponDTO) {
        if ( issuedCouponDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( issuedCouponDTO.getMIdx() );

        return member;
    }

    protected Coupon issuedCouponDTOToCoupon(IssuedCouponDTO issuedCouponDTO) {
        if ( issuedCouponDTO == null ) {
            return null;
        }

        Coupon coupon = new Coupon();

        coupon.setId( issuedCouponDTO.getCpIdx() );

        return coupon;
    }
}
