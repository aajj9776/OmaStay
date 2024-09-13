package com.omakase.omastay.dto.custom;

//필터 조회 결과를 담을 DTO
public class ResultAccommodationsDTO {
    private String hIdx;
    private String h_cate;
    private String hName;
    private Integer oneDayPrice; //평균 가격
    private String img_url;
    private float rating;
    private Integer reviewCount;
    private boolean soldOut;
}
