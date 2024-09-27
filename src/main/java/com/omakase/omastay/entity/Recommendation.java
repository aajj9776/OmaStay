package com.omakase.omastay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recommendation")
@ToString(exclude = {"hostInfo"})
public class Recommendation {
    @Id
    @Column(name = "rec_idx", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Column(name = "rec_type")
    private String recType;

    @Column(name = "rec_point")
    private Float recPoint;

    @Column(name = "rec_date")
    private LocalDateTime recDate;

    @Column(name = "rec_none", length = 100)
    private String recNone;



}