package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
@ToString(exclude = "hostInfo")
public class Account {
    @Id
    @Column(name = "ac_num", nullable = false, length = 100)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo;

    @Column(name = "ac_bank", nullable = false, length = 100)
    private String acBank;

    @Column(name = "ac_name", length = 50)
    private String acName;

    @Column(name = "ac_none", length = 100)
    private String acNone;
}
