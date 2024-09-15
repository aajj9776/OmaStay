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
}
