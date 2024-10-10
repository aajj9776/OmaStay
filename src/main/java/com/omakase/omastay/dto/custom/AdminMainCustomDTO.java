package com.omakase.omastay.dto.custom;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminMainCustomDTO {
    private String name;
    private Long count;

    @QueryProjection
    public AdminMainCustomDTO(String name, Long count){
        this.name = name;
        this.count = count;
    }
}
