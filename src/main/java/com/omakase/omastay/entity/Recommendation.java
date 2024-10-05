package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

import com.omakase.omastay.entity.enumurate.HCate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "recommendation")
@ToString(exclude = {"hostInfo"})
public class Recommendation {
    
    @Id
    @Column(name = "rec_idx", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo;

    @Column(name = "rec_type")
    @Enumerated(EnumType.ORDINAL)
    private HCate recType;

    @Column(name = "rec_point")
    private Integer recPoint;

    @Column(name = "rec_date")
    private LocalDateTime recDate;

    @Column(name = "rec_none", length = 100)
    private String recNone;


    public Recommendation(HostInfo hostInfo, HCate recType, Integer recPoint, LocalDateTime recDate){
        this.hostInfo = hostInfo;
        this.recType = recType;
        this.recPoint = recPoint;
        this.recDate = recDate;
    }
}