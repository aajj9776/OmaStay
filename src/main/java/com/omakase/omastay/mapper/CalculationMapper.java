package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.CalculationDTO;
import com.omakase.omastay.entity.Calculation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CalculationMapper {
    CalculationMapper INSTANCE = Mappers.getMapper(CalculationMapper.class);

    @Mapping(source = "hostInfo.id", target = "HIdx") // 공백 제거
    CalculationDTO toCalculationDTO(Calculation calculation);

    @Mapping(source = "HIdx", target = "hostInfo.id")
    Calculation toCalculation(CalculationDTO calculationDTO);

    List<CalculationDTO> toCalculationDTOList(List<Calculation> calculationList);

    List<Calculation> toCalculationList(List<CalculationDTO> calculationDTOList);
}