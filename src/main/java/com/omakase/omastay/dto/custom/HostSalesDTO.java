package com.omakase.omastay.dto.custom;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.omakase.omastay.entity.enumurate.PayMethod;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostSalesDTO {
    private String roomName;
    private String roomType;
    private StartEndVo startEndVo;
    private String resNum;
    private PayMethod payMethod;
    private LocalDateTime payDate;
    private LocalDate salDate;
    private String nsalePrice;

    @QueryProjection
    public HostSalesDTO(String roomName, String roomType, StartEndVo startEndVo, String resNum, PayMethod payMethod, LocalDateTime payDate, LocalDate salDate, String nsalePrice) {
        this.roomName = roomName;
        this.roomType = roomType;
        this.startEndVo = startEndVo;
        this.resNum = resNum;
        this.payMethod = payMethod;
        this.payDate = payDate;
        this.salDate = salDate;
        this.nsalePrice = nsalePrice;
    }
    
}
