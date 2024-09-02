package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Sales;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class SalesDTO {
    private int id;
    private int hostInfoId;
    private int resIdx;
    private LocalDate salDate;
    private String salNone;

    public SalesDTO(Sales sales) {
        this.id = sales.getId();
        this.hostInfoId = sales.getHostInfo() != null ? sales.getHostInfo().getId() : null;
        this.resIdx = sales.getReservation() != null ? sales.getReservation().getId() : null;
        this.salDate = sales.getSalDate();
        this.salNone = sales.getSalNone();
    }

    @QueryProjection
    public SalesDTO(int id, int hostInfoId, int reservationId, LocalDate salPeriod,
                    LocalDate salDate, String salNone) {
        this.id = id;
        this.hostInfoId = hostInfoId;
        this.resIdx = reservationId;
        this.salDate = salDate;
        this.salNone = salNone;
    }
}