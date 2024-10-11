package com.omakase.omastay.dto.custom;

import java.time.LocalDate;

import com.omakase.omastay.dto.ReservationDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationWithImage {
    private ReservationDTO reservation;
    private String image;
    private String hostName;
    private LocalDate start;
    private LocalDate end;
    private Long date;
    private int hIdx;
    
}
