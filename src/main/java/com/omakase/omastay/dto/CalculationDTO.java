package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Calculation;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CalculationDTO {
    private int id;
    private int hIdx;
    private int calAmount;
    private BooleanStatus calStatus;
    private StartEndVo calStartEnd;
    private LocalDateTime calLegTime;
    private LocalDateTime calConfirmTime;
    private LocalDateTime calCompleteTime;
    private LocalDateTime calCancelTime;
    private String calNone;

    public CalculationDTO(Calculation calculation) {
        this.id = calculation.getId();
        this.hIdx = calculation.getHostInfo() != null ? calculation.getHostInfo().getId() : null;
        this.calAmount = calculation.getCalAmount();
        this.calStatus = calculation.getCalStatus();
        this.calStartEnd = calculation.getCalStartEnd();
        this.calLegTime = calculation.getCalLegTime();
        this.calConfirmTime = calculation.getCalConfirmTime();
        this.calCompleteTime = calculation.getCalCompleteTime();
        this.calCancelTime = calculation.getCalCancelTime();
        this.calNone = calculation.getCalNone();
    }

    @QueryProjection
    public CalculationDTO(int id, int hIdx, int calAmount, BooleanStatus calStatus, StartEndVo calStartEnd, LocalDateTime calLegTime, LocalDateTime calConfirmTime, LocalDateTime calCompleteTime, LocalDateTime calCancelTime, String calNone) {
        this.id = id;
        this.hIdx = hIdx;
        this.calAmount = calAmount;
        this.calStatus = calStatus;
        this.calStartEnd = calStartEnd;
        this.calLegTime = calLegTime;
        this.calConfirmTime = calConfirmTime;
        this.calCompleteTime = calCompleteTime;
        this.calCancelTime = calCancelTime;
        this.calNone = calNone;
    }
}