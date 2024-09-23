package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.RoomInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostRequestInfoDTO {
    private HostInfoDTO hostInfo;
    private AccountDTO account;
    
    private List<FacilitiesDTO> facilities;

    private List<RoomInfoDTO> roomInfo;
    private PriceDTO price;

    private List<ImageDTO> images;
    
}
