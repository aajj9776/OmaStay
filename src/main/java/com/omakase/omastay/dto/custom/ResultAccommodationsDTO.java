package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

//필터 조회 결과를 담을 DTO
@Data
@NoArgsConstructor
public class ResultAccommodationsDTO {
    private Integer hIdx;
    private String h_cate;
    private String hName;
    private Integer oneDayPrice; //평균 가격
    private  String xAxis;
    private String yAxis;
    private String img_url;
    private double rating;
    private Integer reviewCount;
    private boolean soldOut;
}
