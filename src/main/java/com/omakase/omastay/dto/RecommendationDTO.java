package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.entity.enumurate.HCate;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class RecommendationDTO {
    private Integer id;
    private Integer hIdx;
    private HCate recType;
    private Integer recPoint;
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
    public RecommendationDTO(Integer hIdx, HCate recType, Integer recPoint, LocalDateTime recDate) {
        this.hIdx = hIdx;
        this.recType = recType;
        this.recPoint = recPoint;
        this.recDate = recDate;
    }

    @QueryProjection
    public RecommendationDTO(Integer id, Integer hIdx, HCate recType, Integer recPoint, LocalDateTime recDate, String recNone) {
        this.id = id;
        this.hIdx = hIdx;
        this.recType = recType;
        this.recPoint = recPoint;
        this.recDate = recDate;
        this.recNone = recNone;
    }

    @QueryProjection
    public RecommendationDTO(Long hostId, HCate hCate, Integer totalSales, LocalDateTime recommendationDate) {
        this.hIdx = hostId.intValue(); // Assuming hostId is Long and needs to be converted to Integer
        this.recType = hCate;
        this.recPoint = totalSales;
        this.recDate = recommendationDate;
    }
}