package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewDTO {
    private int id;
    private int memIdx;
    private int resIdx;
    private String revWriter;
    private String revContent;
    private LocalDateTime revDate;
    private BooleanStatus revStatus;
    private Float revRating;
    private String revNone;
    private String revFname;
    private String revOname;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.memIdx = review.getMember() != null ? review.getMember().getId() : null;
        this.resIdx = review.getReservation() != null ? review.getReservation().getId() : null;
        this.revWriter = review.getRevWriter();
        this.revContent = review.getRevContent();
        this.revDate = review.getRevDate();
        this.revStatus = review.getRevStatus();
        this.revRating = review.getRevRating();
        this.revNone = review.getRevNone();
    }

    @QueryProjection
    public ReviewDTO(int id, int memberId, int reservationId, String revWriter, String revContent, LocalDateTime revDate,
                     BooleanStatus revStatus, Float revRating, String revNone) {
        this.id = id;
        this.memIdx = memberId;
        this.resIdx = reservationId;
        this.revWriter = revWriter;
        this.revContent = revContent;
        this.revDate = revDate;
        this.revStatus = revStatus;
        this.revRating = revRating;
        this.revNone = revNone;
    }
}