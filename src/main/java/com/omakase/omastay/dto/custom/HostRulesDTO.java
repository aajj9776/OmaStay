package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.PriceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostRulesDTO {
    private HostInfoDTO hostInfo;
    private PriceDTO price;
}
