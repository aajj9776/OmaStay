package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ReservationDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberCustomDTO {
    ReservationDTO reservation;
    HostInfoDTO hostInfo;
}
