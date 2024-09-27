package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.SalesDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesCustomDTO {
    private HostInfoDTO hostInfo;
    private ReservationDTO reservation;
    private PaymentDTO payment;
    private SalesDTO sales;
}
