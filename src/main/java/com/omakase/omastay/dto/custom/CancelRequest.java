package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CancelRequest {
    private String paymentKey;
    private String cancelReason;
    
}
