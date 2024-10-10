package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelRequestDTO {
    private String paymentKey;
    private String cancelReason;
    private int payIdx;
    private int resIdx;
    private Integer icIdx;
    private Integer pIdx;
    private Integer memIdx;
    
    
}
