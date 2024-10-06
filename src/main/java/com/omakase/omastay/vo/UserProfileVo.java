package com.omakase.omastay.vo;


import com.omakase.omastay.entity.enumurate.BooleanStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class UserProfileVo {

    @Column(name = "ad_pw", nullable = false, length = 100)
    private String pw;

    @Column(name = "ad_email", nullable = false, length = 100)
    private String email;

    // TRUE = 탈퇴 안함 / FALSE = 탈퇴함
    @Column(name = "ad_status", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private BooleanStatus status;
}
