package com.omakase.omastay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recommendation")
@ToString(exclude = {"hostInfo"})
public class Recommendation {
    @Id
    @Column(name = "rec_idx", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo;

    @Column(name = "rec_point")
    private Float recPoint;

    @Column(name = "rec_none", length = 100)
    private String recNone;



}