package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.entity.RoomInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomInfoMapper {
    RoomInfoMapper INSTANCE = Mappers.getMapper(RoomInfoMapper.class);

    @Mapping(source = "hostInfo.id", target = "hostInfoId")
    RoomInfoDTO toRoomInfoDTO(RoomInfo roomInfo);

    @Mapping(source = "hostInfoId", target = "hostInfo.id")
    RoomInfo toRoomInfo(RoomInfoDTO roomInfoDTO);

    List<RoomInfoDTO> toRoomInfoDTOList(List<RoomInfo> roomInfoList);

    List<RoomInfo> toRoomInfoList(List<RoomInfoDTO> roomInfoDTOList);
}