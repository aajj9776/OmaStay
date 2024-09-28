package com.omakase.omastay.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
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

    @Lob
    @Column(name = "h_intro")
    private String hintro;
}
