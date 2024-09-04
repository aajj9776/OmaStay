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
    private int id;
    private int hIdx;
    private int riIdx;
    private int regularPrice;
    private PeakVo peakVo;
    private SemiPeakVo semi;
    private int peakSet;
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
    public PriceDTO(int id, int hIdx, int riIdx, int regularPrice, PeakVo peakVo, SemiPeakVo semi, int peakSet, String priNone) {
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