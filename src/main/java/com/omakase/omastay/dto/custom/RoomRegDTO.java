package com.omakase.omastay.dto.custom;

import java.util.List;

import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.RoomInfoDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRegDTO {
    private RoomInfoDTO roomInfo;
    private PriceDTO price;
    private List<ImageDTO> images;
}
