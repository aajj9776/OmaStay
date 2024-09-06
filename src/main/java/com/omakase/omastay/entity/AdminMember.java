package com.omakase.omastay.entity;

import com.omakase.omastay.vo.UserProfileVo;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "admin_member")
@ToString
@Builder
public class AdminMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_idx", nullable = false)
    private int id;

    @Column(name = "ad_id", nullable = false, length = 100)
    private String adId;

    //이메일, 비밀번호, 회원가입탈퇴 상태
    @Embedded
    private UserProfileVo adminProfile = new UserProfileVo();

    @Column(name = "ad_auth", nullable = false)
    private int adAuth;

    @Column(name = "ad_none", length = 100)
    private String adNone;
}