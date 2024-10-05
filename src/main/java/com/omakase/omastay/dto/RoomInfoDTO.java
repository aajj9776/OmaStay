package com.omakase.omastay.dto;

import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomInfoDTO {
    private Integer id;
    private Integer hIdx;
    private String roomName;
    private String roomType;
    private Integer roomPerson;
    private String roomIntro;
    private BooleanStatus roomStatus;
    private String roomNone;

    public RoomInfoDTO(RoomInfo roomInfo) {
        this.id = roomInfo.getId();
        this.hIdx = roomInfo.getHostInfo() != null ? roomInfo.getHostInfo().getId() : null;
        this.roomName = roomInfo.getRoomName();
        this.roomType = roomInfo.getRoomType();
        this.roomPerson = roomInfo.getRoomPerson();
        this.roomIntro = roomInfo.getRoomIntro();
        this.roomStatus = roomInfo.getRoomStatus();
        this.roomNone = roomInfo.getRoomNone();
    }

    @QueryProjection
    public RoomInfoDTO(Integer id, Integer hIdx, String roomName, String roomType, String riIntro, BooleanStatus roomStatus, String roomNone, Integer roomPerson) {
        this.id = id;
        this.hIdx = hIdx;
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomIntro = riIntro;
        this.roomPerson = roomPerson;
        this.roomStatus = roomStatus;
        this.roomNone = roomNone;
    }
}