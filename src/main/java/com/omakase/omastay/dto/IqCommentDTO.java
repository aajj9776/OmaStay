package com.omakase.omastay.dto;

import com.omakase.omastay.entity.IqComment;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class IqCommentDTO {
    private int id;
    private int iqIdx;
    private String iqcTitle;
    private String iqcContent;
    private LocalDateTime iqcDate;
    private String iqcNone;

    public IqCommentDTO(IqComment iqComment) {
        this.id = iqComment.getId();
        this.iqIdx = iqComment.getInquiry() != null ? iqComment.getInquiry().getId() : null;
        this.iqcTitle = iqComment.getIqcTitle();
        this.iqcContent = iqComment.getIqcContent();
        this.iqcDate = iqComment.getIqcDate();
        this.iqcNone = iqComment.getIqcNone();
    }

    @QueryProjection
    public IqCommentDTO(int id, int iqIdx, String iqcTitle, String iqcContent, LocalDateTime iqcDate, String iqcNone) {
        this.id = id;
        this.iqIdx = iqIdx;
        this.iqcTitle = iqcTitle;
        this.iqcContent = iqcContent;
        this.iqcDate = iqcDate;
        this.iqcNone = iqcNone;
    }
}