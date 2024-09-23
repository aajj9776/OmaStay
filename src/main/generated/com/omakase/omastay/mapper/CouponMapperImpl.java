package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.entity.Coupon;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T16:50:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class CouponMapperImpl implements CouponMapper {

    @Override
    public CouponDTO toCouponDTO(Coupon coupon) {
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

    @Override
    public Coupon toCoupon(CouponDTO cuponDTO) {
        if ( cuponDTO == null ) {
            return null;
        }

        Coupon coupon = new Coupon();

        coupon.setId( cuponDTO.getId() );
        coupon.setCpContent( cuponDTO.getCpContent() );
        coupon.setCpStartEnd( cuponDTO.getCpStartEnd() );
        coupon.setCpSale( cuponDTO.getCpSale() );
        coupon.setCpCate( cuponDTO.getCpCate() );
        coupon.setCpMethod( cuponDTO.getCpMethod() );
        coupon.setCpNone( cuponDTO.getCpNone() );

        return coupon;
    }

    @Override
    public List<CouponDTO> toCouponDTOList(List<Coupon> cuponList) {
        if ( cuponList == null ) {
            return null;
        }

        List<CouponDTO> list = new ArrayList<CouponDTO>( cuponList.size() );
        for ( Coupon coupon : cuponList ) {
            list.add( toCouponDTO( coupon ) );
        }

        return list;
    }

    @Override
    public List<Coupon> toCouponList(List<CouponDTO> cuponDTOList) {
        if ( cuponDTOList == null ) {
            return null;
        }

        List<Coupon> list = new ArrayList<Coupon>( cuponDTOList.size() );
        for ( CouponDTO couponDTO : cuponDTOList ) {
            list.add( toCoupon( couponDTO ) );
        }

        return list;
    }
}
