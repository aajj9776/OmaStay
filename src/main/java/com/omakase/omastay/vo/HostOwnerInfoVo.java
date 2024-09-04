package com.omakase.omastay.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HostOwnerInfoVo {
    @Column(name = "h_name", nullable = false, length = 100)
    private String hName;

    @Column(name = "h_email", nullable = false, length = 100)
    private String hEmail;

    @Column(name = "h_phone", nullable = false, length = 100)
    private String hPhone;

    @Column(name = "host_name", length = 100)
    private String hostName;

    @Column(name = "h_intro", length = 100)
    private String hIntro;
}
