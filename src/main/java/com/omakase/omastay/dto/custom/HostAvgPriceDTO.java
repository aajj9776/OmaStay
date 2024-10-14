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
    private boolean soldOut;

    public HostAvgPriceDTO(Integer id, Integer avgPrice) {
        this.hostIdx = id;
        this.avgPrice = avgPrice;
    }
}
