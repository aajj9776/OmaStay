package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Calculation;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.CalStatus;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CalculationDTO {
    private Integer id;
    private Integer hIdx;
    private Integer calAmount;
    private CalStatus calStatus;
    private LocalDateTime calMonth;
    private LocalDateTime calRegTime;
    private LocalDateTime calConfirmTime;
    private LocalDateTime calCompleteTime;
    private String calNone;

    private String hname;

    public CalculationDTO(Calculation calculation) {
        this.id = calculation.getId();
        this.hIdx = calculation.getHostInfo() != null ? calculation.getHostInfo().getId() : null;
        this.calAmount = calculation.getCalAmount();
        this.calStatus = calculation.getCalStatus();
        this.calMonth = calculation.getCalMonth();
        this.calRegTime = calculation.getCalRegTime();
        this.calConfirmTime = calculation.getCalConfirmTime();
        this.calCompleteTime = calculation.getCalCompleteTime();
        this.calNone = calculation.getCalNone();
    }

    @QueryProjection
    public CalculationDTO(Integer id, Integer hIdx, Integer calAmount, CalStatus calStatus, LocalDateTime calMonth, LocalDateTime calRegTime, LocalDateTime calConfirmTime, LocalDateTime calCompleteTime, String calNone) {
        this.id = id;
        this.hIdx = hIdx;
        this.calAmount = calAmount;
        this.calStatus = calStatus;
        this.calMonth = calMonth;
        this.calRegTime = calRegTime;
        this.calConfirmTime = calConfirmTime;
        this.calCompleteTime = calCompleteTime;
        this.calNone = calNone;
    }
}