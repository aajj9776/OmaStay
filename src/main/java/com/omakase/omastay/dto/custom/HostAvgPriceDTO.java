package com.omakase.omastay.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostAvgPriceDTO {
    private Integer hostIdx;
    private Integer avgPrice;
}
