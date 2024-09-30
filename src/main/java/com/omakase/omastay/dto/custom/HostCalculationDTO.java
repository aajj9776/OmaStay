package com.omakase.omastay.dto.custom;

import com.omakase.omastay.entity.enumurate.CalStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostCalculationDTO {
    private Integer year; //년
    private Integer month; //월
    private Integer salCount; //매출건수
    private Integer salAmount; //매출금액
    private Integer commission; //수수료
    private Integer calAmount; //정산금액
    private CalStatus calStatus; //정산상태

}
