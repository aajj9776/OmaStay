package com.omakase.omastay.mapper;
import com.omakase.omastay.dto.CouponDTO;
import com.omakase.omastay.entity.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CouponMapper {
    CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

    CouponDTO toCouponDTO(Coupon coupon);

    Coupon toCoupon(CouponDTO cuponDTO);

    List<CouponDTO> toCouponDTOList(List<Coupon> cuponList);

    List<Coupon> toCouponList(List<CouponDTO> cuponDTOList);
}
