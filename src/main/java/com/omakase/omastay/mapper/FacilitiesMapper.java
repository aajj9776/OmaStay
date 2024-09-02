package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.entity.Facilities;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FacilitiesMapper {
    FacilitiesMapper INSTANCE = Mappers.getMapper(FacilitiesMapper.class);

    FacilitiesDTO toFacilitiesDTO(Facilities facilities);

    Facilities toFacilities(FacilitiesDTO facilitiesDTO);

    List<FacilitiesDTO> toFacilitiesDTOList(List<Facilities> facilitiesList);

    List<Facilities> toFacilitiesList(List<FacilitiesDTO> facilitiesDTOList);
}