package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Grade;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class GradeDTO {
    private Integer id;
    private String gCate;
    private String gReq;
    private String gSale;
    private String gPoint;
    private String gNone;

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.gCate = grade.getGCate();
        this.gReq = grade.getGReq();
        this.gSale = grade.getGSale();
        this.gPoint = grade.getGPoint();
    }

    @QueryProjection
    public GradeDTO(int id, String gCate, String gReq, String gSale, String gPoint, String gNone) {
        this.id = id;
        this.gCate = gCate;
        this.gReq = gReq;
        this.gSale = gSale;
        this.gPoint = gPoint;
        this.gNone = gNone;
    }
}