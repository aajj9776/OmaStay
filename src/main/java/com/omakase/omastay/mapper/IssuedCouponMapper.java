package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.entity.IssuedCoupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IssuedCouponMapper {
    IssuedCouponMapper INSTANCE = Mappers.getMapper(IssuedCouponMapper.class);

    @Mapping(source = "member.id", target = "MIdx")
    @Mapping(source = "coupon.id", target = "cpIdx")
    IssuedCouponDTO ㅡ드toIssuedCouponDTO(IssuedCoupon issuedCoupon);

    @Mapping(source = "MIdx", target = "member.id")
    @Mapping(source = "cpIdx", target = "coupon.id")
    IssuedCoupon toIssuedCoupon(IssuedCouponDTO issuedCouponDTO);

    List<IssuedCouponDTO> toIssuedCouponDTOList(List<IssuedCoupon> issuedCouponList);

    List<IssuedCoupon> toIssuedCouponList(List<IssuedCouponDTO> issuedCouponDTOList);
}