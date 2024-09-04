package com.omakase.omastay.dto;

import com.omakase.omastay.entity.RoomFacilities;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Data
@NoArgsConstructor
public class RoomFacilitiesDTO {
    private int id;
    private int fIdx;
    private int riIdx;
    private String roomNone;

    public RoomFacilitiesDTO(RoomFacilities roomFacilities) {
        this.id = roomFacilities.getId();
        this.fIdx = roomFacilities.getFacilities() != null ? roomFacilities.getFacilities().getId() : null;
        this.riIdx = roomFacilities.getRoomInfo() != null ? roomFacilities.getRoomInfo().getId() : null;
        this.roomNone = roomFacilities.getRoomNone();
    }

    @QueryProjection
    public RoomFacilitiesDTO(Integer id, Integer facilitiesId, Integer roomInfoId, String roomNone) {
        this.id = id;
        this.fIdx = facilitiesId;
        this.riIdx = roomInfoId;
        this.roomNone = roomNone;
    }
}