package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.SalesDTO;
import com.omakase.omastay.entity.Sales;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SalesMapper {
    SalesMapper INSTANCE = Mappers.getMapper(SalesMapper.class);

    @Mapping(source = "hostInfo.id", target = "hostInfoId")
    @Mapping(source = "reservation.id", target = "resIdx")
    SalesDTO toSalesDTO(Sales sales);

    @Mapping(source = "hostInfoId", target = "hostInfo.id")
    @Mapping(source = "resIdx", target = "reservation.id")
    Sales toSales(SalesDTO salesDTO);

    List<SalesDTO> toSalesDTOList(List<Sales> salesList);

    List<Sales> toSalesList(List<SalesDTO> salesDTOList);
}