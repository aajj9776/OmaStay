package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Account;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDTO {
    private int id;
    private String acNum;
    private int hidx;
    private String acBank;
    private String acName;
    private String acNone;


    public AccountDTO(Account account) {
        this.acNum = account.getAcNum();
        this.hidx = account.getHostInfo() != null ? account.getHostInfo().getId() : null;
        this.acBank = account.getAcBank();
        this.acName = account.getAcName();
        this.acNone = account.getAcNone();
    }


    @QueryProjection
    public AccountDTO(int id, String acNum, int hidx, String acBank, String acName, String acNone) {
        this.id = id;
        this.acNum = acNum;
        this.hidx = hidx;
        this.acBank = acBank;
        this.acName = acName;
        this.acNone = acNone;
    }
}