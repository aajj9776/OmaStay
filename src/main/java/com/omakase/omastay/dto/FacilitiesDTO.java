package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Facilities;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class FacilitiesDTO {
    private int id;
    private String fCate;
    private String fNone;

    public FacilitiesDTO(Facilities facilities) {
        this.id = facilities.getId();
        this.fCate = facilities.getFCate();
        this.fNone = facilities.getFNone();
    }

    @QueryProjection
    public FacilitiesDTO(int id, String fCate, String fNone) {
        this.id = id;
        this.fCate = fCate;
        this.fNone = fNone;
    }
}