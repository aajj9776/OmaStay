package com.omakase.omastay.dto;

import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.RiStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomInfoDTO {
    private int id;
    private int hostInfoId;
    private String riName;
    private String riType;
    private int riCount;
    private String riIntro;
    private RiStatus riStatus;
    private String riNone;
    private int riPerson;

    public RoomInfoDTO(RoomInfo roomInfo) {
        this.id = roomInfo.getId();
        this.hostInfoId = roomInfo.getHostInfo() != null ? roomInfo.getHostInfo().getId() : null;
        this.riName = roomInfo.getRiName();
        this.riType = roomInfo.getRiType();
        this.riCount = roomInfo.getRiCount();
        this.riIntro = roomInfo.getRiIntro();
        this.riStatus = roomInfo.getRiStatus();
        this.riNone = roomInfo.getRiNone();
        this.riPerson = roomInfo.getRiPerson();
    }

    @QueryProjection
    public RoomInfoDTO(int id, int hostInfoId, String riName, String riType, int riCount, String riIntro, RiStatus riStatus, String riNone, int riPerson) {
        this.id = id;
        this.hostInfoId = hostInfoId;
        this.riName = riName;
        this.riType = riType;
        this.riCount = riCount;
        this.riIntro = riIntro;
        this.riStatus = riStatus;
        this.riNone = riNone;
        this.riPerson = riPerson;
    }
}