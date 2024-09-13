package com.omakase.omastay.dto.custom;

import java.util.List;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostInfoCustomDTO {
    private HostInfoDTO hostInfo;
    private List<FacilitiesDTO> facilities;
    private List<ImageDTO> images;

    public HostInfoCustomDTO(HostInfoDTO hostInfo, List<FacilitiesDTO> facilities, List<ImageDTO> images) {
        this.hostInfo = hostInfo;
        this.facilities= facilities;
        this.images = images;
    }

}
