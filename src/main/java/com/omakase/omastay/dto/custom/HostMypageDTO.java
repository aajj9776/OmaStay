package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.dto.HostInfoDTO;

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
