package com.omakase.omastay.dto;

import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewCommentDTO {
    private Integer id;
    private Integer revIdx;
    private String rcComment;
    private LocalDateTime rcDate;
    private BooleanStatus rcStatus;
    private String rcNone;

    public ReviewCommentDTO(ReviewComment reviewComment) {
        this.id = reviewComment.getId();
        this.revIdx = reviewComment.getReview() != null ? reviewComment.getReview().getId() : null;
        this.rcComment = reviewComment.getRcComment();
        this.rcDate = reviewComment.getRcDate();
        this.rcStatus = reviewComment.getRcStatus();
        this.rcNone = reviewComment.getRcNone();
    }

    @QueryProjection
    public ReviewCommentDTO(Integer id, Integer revIdx, String rcComment, LocalDateTime rcDate, BooleanStatus rcStatus, String rcNone) {
        this.id = id;
        this.revIdx = revIdx;
        this.rcComment = rcComment;
        this.rcDate = rcDate;
        this.rcStatus = rcStatus;
        this.rcNone = rcNone;
    }
}