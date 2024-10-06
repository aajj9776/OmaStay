package com.omakase.omastay.dto;

import com.omakase.omastay.entity.NonMember;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class NonMemberDTO {
    private Integer id;
    private String nonName;
    private String nonPhone;
    private String nonEmail;
    private String nonNone;

    public NonMemberDTO(NonMember nonMember) {
        this.id = nonMember.getId();
        this.nonName = nonMember.getNonName();
        this.nonPhone = nonMember.getNonPhone();
        this.nonEmail = nonMember.getNonEmail();
        this.nonNone = nonMember.getNonNone();
    }

    @QueryProjection
    public NonMemberDTO(Integer id, String nonName, String nonPhone, String nonEmail, String nonNone) {
        this.id = id;
        this.nonName = nonName;
        this.nonPhone = nonPhone;
        this.nonEmail = nonEmail;
        this.nonNone = nonNone;
    }
}