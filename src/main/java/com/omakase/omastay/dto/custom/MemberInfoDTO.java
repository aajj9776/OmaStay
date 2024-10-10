package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInfoDTO {
    private int id;
    private String email;
    private Long exp;
    private String memName;
    private String sub;
    public MemberInfoDTO(int id, String email, Long exp, String memName, String sub) {
        this.id = id;
        this.email = email;
        this.exp = exp;
        this.memName = memName;
        this.sub = sub;
    }
}
