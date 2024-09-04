package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "hostInfo.id", target = "HIdx")
    @Mapping(source = "roomInfo.id", target = "riIdx")
    PriceDTO toPriceDTO(Price price);

    @Mapping(source = "HIdx", target = "hostInfo.id")
    @Mapping(source = "riIdx", target = "roomInfo.id")
    Price toPrice(PriceDTO priceDTO);

    List<PriceDTO> toPriceDTOList(List<Price> priceList);

    List<Price> toPriceList(List<PriceDTO> priceDTOList);
}