package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Visitor;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class VisitorDTO {
    private int id;
    private String vIp;
    private LocalDateTime vDate;
    private String vRefer;
    private String vAgent;
    private String vNone;

    public VisitorDTO(Visitor visitor) {
        this.id = visitor.getId();
        this.vIp = visitor.getVIp();
        this.vDate = visitor.getVDate();
        this.vRefer = visitor.getVRefer();
        this.vAgent = visitor.getVAgent();
        this.vNone = visitor.getVNone();
    }

    @QueryProjection
    public VisitorDTO(int id, String vIp, String vRefer, String vAgent, String vNone, LocalDateTime vDate) {
        this.id = id;
        this.vIp = vIp;
        this.vDate = vDate;
        this.vRefer = vRefer;
        this.vAgent = vAgent;
        this.vNone = vNone;
    }
}