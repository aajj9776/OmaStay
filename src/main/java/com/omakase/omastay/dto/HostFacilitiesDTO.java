package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.HostFacilities;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class HostFacilitiesDTO {
    private Integer id;
    private Integer fIdx;
    private Integer hIdx;
    private String hostNone;

    public HostFacilitiesDTO(HostFacilities hostFacilities) {
        this.id = hostFacilities.getId();
        this.fIdx = hostFacilities.getFacilities() != null ? hostFacilities.getFacilities().getId() : null;
        this.hIdx = hostFacilities.getHostInfo() != null ? hostFacilities.getHostInfo().getId() : null;
        this.hostNone = hostFacilities.getRoomNone();
    }

    @QueryProjection
    public HostFacilitiesDTO(Integer id, Integer facilitiesId, Integer hostInfoId, String hostNone) {
        this.id = id;
        this.fIdx = facilitiesId;
        this.hIdx = hostInfoId;
        this.hostNone = hostNone;
    }
}