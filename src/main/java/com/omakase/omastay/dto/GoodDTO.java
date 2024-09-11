package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Good;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GoodDTO {
    private Integer id;
    private Integer revIdx;
    private Integer memIdx;
    private LocalDateTime goodDate;
    private BooleanStatus goodStatus;
    private String goodNone;

    public GoodDTO(Good good) {
        this.id = good.getId();
        this.revIdx = good.getReview() != null ? good.getReview().getId() : null;
        this.memIdx = good.getMember() != null ? good.getMember().getId() : null;
        this.goodDate = good.getGoodDate();
        this.goodStatus = good.getGoodStatus();
        this.goodNone = good.getGoodNone();
    }

    @QueryProjection
    public GoodDTO(Integer id, Integer revIdx, Integer memIdx, LocalDateTime goodDate, BooleanStatus goodStatus, String goodNone) {
        this.id = id;
        this.revIdx = revIdx;
        this.memIdx = memIdx;
        this.goodDate = goodDate;
        this.goodStatus = goodStatus;
        this.goodNone = goodNone;
    }
}