package com.omakase.omastay.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostMypageDTO {
    private AccountDTO account;
    private HostInfoDTO hostInfo;
    private String pw;

    public HostMypageDTO(AccountDTO account, HostInfoDTO hostInfo) {
        this.account = account;
        this.hostInfo = hostInfo;
    }
    
}
