package com.omakase.omastay.dto.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

// 필터 조회 결과를 담을 DTO
@Data
@NoArgsConstructor
public class ResultAccommodationsDTO {
    @JsonProperty("hidx")
    private Integer hIdx;

    @JsonProperty("hcate")
    private String hCate;

    @JsonProperty("hname")
    private String hName;

    @JsonProperty("oneDayPrice")
    private Integer oneDayPrice; //평균 가격

    @JsonProperty("xaxis")
    private String xAxis;

    @JsonProperty("yaxis")
    private String yAxis;

    @JsonProperty("img_url")
    private String img_url;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("reviewCount")
    private int reviewCount;

    @JsonProperty("soldOut")
    private boolean soldOut;
}