package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.entity.RoomFacilities;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomFacilitiesMapper {
    RoomFacilitiesMapper INSTANCE = Mappers.getMapper(RoomFacilitiesMapper.class);

    @Mapping(source = "roomInfo.id", target = "id")
    @Mapping(source = "roomInfo.hostInfo.id", target = "hostInfoId")
    @Mapping(source = "roomInfo.riName", target = "riName")
    @Mapping(source = "roomInfo.riType", target = "riType")
    @Mapping(source = "roomInfo.riCount", target = "riCount")
    @Mapping(source = "roomInfo.riIntro", target = "riIntro")
    @Mapping(source = "roomInfo.riStatus", target = "riStatus")
    @Mapping(source = "roomInfo.riNone", target = "riNone")
    @Mapping(source = "roomInfo.riPerson", target = "riPerson")
    RoomInfoDTO toRoomInfoDTO(RoomFacilities roomFacilities);

    List<RoomInfoDTO> toRoomInfoDTOList(List<RoomFacilities> roomFacilitiesList);
}