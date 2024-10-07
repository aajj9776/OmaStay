package com.omakase.omastay.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HostFacilitiesMapper {
    HostFacilitiesMapper INSTANCE = Mappers.getMapper(HostFacilitiesMapper.class);

}