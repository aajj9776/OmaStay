package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.entity.HostInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HostInfoMapper {
    HostInfoMapper INSTANCE = Mappers.getMapper(HostInfoMapper.class);

    @Mapping(source = "adminMember.id", target = "adIdx")
    @Mapping(source = "hostAddress", target = "addressVo")
    @Mapping(source = "hostOwnerInfo", target = "hostOwnerInfo")
    HostInfoDTO toHostInfoDTO(HostInfo hostInfo);

    @Mapping(source = "adIdx", target = "adminMember.id")
    @Mapping(source = "addressVo", target = "hostAddress")
    @Mapping(source = "hostOwnerInfo", target = "hostOwnerInfo")
    HostInfo toHostInfo(HostInfoDTO hostInfoDTO);
}