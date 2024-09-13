package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Point;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PointDTO {
    private Integer id;
    private Integer memIdx; // 속성 이름이 mIdx인지 확인
    private Integer pSum;
    private Integer pValue;
    private LocalDateTime pDate;
    private String pNone;

    public PointDTO(Point point) {
        this.id = point.getId();
        this.memIdx = point.getMember() != null ? point.getMember().getId() : null;
        this.pSum = point.getPSum();
        this.pValue = point.getPValue();
        this.pDate = point.getPDate();
        this.pNone = point.getPNone();
    }

    @QueryProjection
    public PointDTO(Integer id, Integer memIdx, Integer pSum, Integer pValue, LocalDateTime pDate, String pNone) {
        this.id = id;
        this.memIdx = memIdx;
        this.pSum = pSum;
        this.pValue = pValue;
        this.pDate = pDate;
        this.pNone = pNone;
    }
}