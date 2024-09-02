package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Recommendation;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

@Data
@NoArgsConstructor
public class RecommendationDTO {
    private int id;
    private int hIdx;
    private Float recPoint;
    private String recNone;

    public RecommendationDTO(Recommendation recommendation) {
        this.id = recommendation.getId();
        this.hIdx = recommendation.getHostInfo().getId();
        this.recPoint = recommendation.getRecPoint();
        this.recNone = recommendation.getRecNone();
    }

    @QueryProjection
    public RecommendationDTO(int id, Float recPoint, String recNone) {
        this.id = id;
        this.recPoint = recPoint;
        this.recNone = recNone;
    }
}