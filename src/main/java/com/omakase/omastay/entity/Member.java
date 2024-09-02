package com.omakase.omastay.entity;

import com.omakase.omastay.vo.AddressVo;
import com.omakase.omastay.vo.UserProfileVo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.Gender;
import com.omakase.omastay.entity.enumurate.Social;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
@ToString(exclude = {"grade"})
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "g_idx", referencedColumnName = "g_idx")
    private Grade grade;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "mem_email", nullable = false, length = 100)),
            @AttributeOverride(name = "pw", column = @Column(name = "mem_pw", nullable = false, length = 1024)),
            @AttributeOverride(name = "status", column = @Column(name = "mem_status", nullable = false))
    })
    private UserProfileVo memberProfile;

    @Column(name = "mem_phone", nullable = false, length = 100)
    private String memPhone;

    @Column(name = "mem_name", nullable = false, length = 50)
    private String memName;

    //FALSE: 비동의, TRUE: 동의
    @Enumerated
    @Column(name = "mem_email_check", nullable = false)
    private BooleanStatus memEmailCheck;

    @Column(name = "mem_birth", nullable = false, length = 100)
    private String memBirth;

    @Column(name = "mem_join_date", nullable = false, updatable = false)
    private LocalDateTime memJoinDate;

    // 0: 소셜아님 1: 카카오 2: 네이버 3: 구글
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "mem_social", nullable = false)
    private Social memSocial;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "postCode", column = @Column(name = "mem_post_code", nullable = false, length = 200)),
            @AttributeOverride(name = "street", column = @Column(name = "mem_street", nullable = false, length = 200)),
            @AttributeOverride(name = "detail", column = @Column(name = "mem_detail", nullable = false, length = 200))
    })
    private AddressVo addressVo;

    @Enumerated(EnumType.STRING)
    @Column(name = "mem_gender", nullable = false, length = 10, updatable = false)
    private Gender memGender;

    @Column(name = "access_token", length = 1024)
    private String accessToken;

    @Column(name = "refresh_token", length = 1024)
    private String refreshToken;

    @Column(name = "mem_none", length = 100)
    private String memNone;

}