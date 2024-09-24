package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.RoomInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:36+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class RoomInfoMapperImpl implements RoomInfoMapper {

    @Override
    public RoomInfoDTO toRoomInfoDTO(RoomInfo roomInfo) {
        if ( roomInfo == null ) {
            return null;
        }

        RoomInfoDTO roomInfoDTO = new RoomInfoDTO();

        roomInfoDTO.setHIdx( roomInfoHostInfoId( roomInfo ) );
        roomInfoDTO.setId( roomInfo.getId() );
        roomInfoDTO.setRoomName( roomInfo.getRoomName() );
        roomInfoDTO.setRoomType( roomInfo.getRoomType() );
        roomInfoDTO.setRoomPerson( roomInfo.getRoomPerson() );
        roomInfoDTO.setRoomIntro( roomInfo.getRoomIntro() );
        roomInfoDTO.setRoomStatus( roomInfo.getRoomStatus() );
        roomInfoDTO.setRoomNone( roomInfo.getRoomNone() );

        return roomInfoDTO;
    }

    @Override
    public RoomInfo toRoomInfo(RoomInfoDTO roomInfoDTO) {
        if ( roomInfoDTO == null ) {
            return null;
        }

        RoomInfo roomInfo = new RoomInfo();

        roomInfo.setHostInfo( roomInfoDTOToHostInfo( roomInfoDTO ) );
        roomInfo.setId( roomInfoDTO.getId() );
        roomInfo.setRoomName( roomInfoDTO.getRoomName() );
        roomInfo.setRoomType( roomInfoDTO.getRoomType() );
        roomInfo.setRoomIntro( roomInfoDTO.getRoomIntro() );
        roomInfo.setRoomStatus( roomInfoDTO.getRoomStatus() );
        if ( roomInfoDTO.getRoomPerson() != null ) {
            roomInfo.setRoomPerson( roomInfoDTO.getRoomPerson() );
        }
        roomInfo.setRoomNone( roomInfoDTO.getRoomNone() );

        return roomInfo;
    }

    @Override
    public List<RoomInfoDTO> toRoomInfoDTOList(List<RoomInfo> roomInfoList) {
        if ( roomInfoList == null ) {
            return null;
        }

        List<RoomInfoDTO> list = new ArrayList<RoomInfoDTO>( roomInfoList.size() );
        for ( RoomInfo roomInfo : roomInfoList ) {
            list.add( toRoomInfoDTO( roomInfo ) );
        }

        return list;
    }

    @Override
    public List<RoomInfo> toRoomInfoList(List<RoomInfoDTO> roomInfoDTOList) {
        if ( roomInfoDTOList == null ) {
            return null;
        }

        List<RoomInfo> list = new ArrayList<RoomInfo>( roomInfoDTOList.size() );
        for ( RoomInfoDTO roomInfoDTO : roomInfoDTOList ) {
            list.add( toRoomInfo( roomInfoDTO ) );
        }

        return list;
    }

    private Integer roomInfoHostInfoId(RoomInfo roomInfo) {
        if ( roomInfo == null ) {
            return null;
        }
        HostInfo hostInfo = roomInfo.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected HostInfo roomInfoDTOToHostInfo(RoomInfoDTO roomInfoDTO) {
        if ( roomInfoDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( roomInfoDTO.getHIdx() );

        return hostInfo;
    }
}
