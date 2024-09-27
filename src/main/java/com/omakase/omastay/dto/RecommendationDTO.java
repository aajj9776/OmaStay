package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Recommendation;
import com.querydsl.core.annotations.QueryProjection;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecommendationDTO {
    private Integer id;
    private Integer hIdx;
    private String recType;
    private Float recPoint;
    private LocalDateTime recDate;
    private String recNone;

    public RecommendationDTO(Recommendation recommendation) {
        this.id = recommendation.getId();
        this.hIdx = recommendation.getHostInfo().getId();
        this.recType = recommendation.getRecType();
        this.recPoint = recommendation.getRecPoint();
        this.recDate = recommendation.getRecDate();
        this.recNone = recommendation.getRecNone();
    }

    @QueryProjection
    public RecommendationDTO(Integer id, String recType, Float recPoint, LocalDateTime recDate, String recNone) {
        this.id = id;
        this.recType = recType;
        this.recPoint = recPoint;
        this.recDate = recDate;
        this.recNone = recNone;
    }
}