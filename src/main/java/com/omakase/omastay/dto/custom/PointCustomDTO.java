package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PointDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointCustomDTO {
    private PointDTO point;
    private MemberDTO member;
}
