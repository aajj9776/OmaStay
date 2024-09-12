package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Price;
import com.omakase.omastay.vo.PeakVo;
import com.omakase.omastay.vo.SemiPeakVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceDTO {
    private Integer id;
    private Integer hIdx;
    private Integer riIdx;
    private Integer regularPrice;
    private PeakVo peakVo = new PeakVo();
    private SemiPeakVo semi = new SemiPeakVo();
    private Integer peakSet;
    private String priNone;

    public PriceDTO(Price price) {
        this.id = price.getId();
        this.hIdx = price.getHostInfo() != null ? price.getHostInfo().getId() : null;
        this.riIdx = price.getRoomInfo() != null ? price.getRoomInfo().getId() : null;
        this.regularPrice = price.getRegularPrice();
        this.peakVo = price.getPeakVo();
        this.semi = price.getSemi();
        this.peakSet = price.getPeakSet();
        this.priNone = price.getPriNone();
    }

    @QueryProjection
    public PriceDTO(Integer id, Integer hIdx, Integer riIdx, Integer regularPrice, PeakVo peakVo, SemiPeakVo semi, Integer peakSet, String priNone) {
        this.id = id;
        this.hIdx = hIdx;
        this.riIdx = riIdx;
        this.regularPrice = regularPrice;
        this.peakVo = peakVo;
        this.semi = semi;
        this.peakSet = peakSet;
        this.priNone = priNone;
    }
}