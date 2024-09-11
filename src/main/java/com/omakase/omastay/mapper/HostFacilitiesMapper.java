package com.omakase.omastay.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HostFacilitiesMapper {
    HostFacilitiesMapper INSTANCE = Mappers.getMapper(HostFacilitiesMapper.class);

}