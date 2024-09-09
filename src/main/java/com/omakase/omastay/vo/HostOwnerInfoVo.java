package com.omakase.omastay.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class HostOwnerInfoVo {
    @Column(name = "host_name", length = 100)
    private String hostName;

    @Column(name = "h_intro", length = 100)
    private String hIntro;
}
