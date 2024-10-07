package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.ReservationDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationWithImage {
    private ReservationDTO reservation;
    private String image;
    private String hostName;
    
}
