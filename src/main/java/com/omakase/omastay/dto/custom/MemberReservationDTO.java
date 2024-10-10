package com.omakase.omastay.dto.custom;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberReservationDTO {
    private Integer memberId;
    private String memberName;
    private String memberEmail;
    private String grade;    // 등급
    private String imageUrl; // 등급 이미지

    // 간단한 생성자
    public MemberReservationDTO(Integer memberId, String memberName, String memberEmail, String grade, String imageUrl) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.grade = grade;
        this.imageUrl = imageUrl;
    }
}
