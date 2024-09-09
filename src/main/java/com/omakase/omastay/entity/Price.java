package com.omakase.omastay.entity;

import com.omakase.omastay.vo.PeakVo;
import com.omakase.omastay.vo.SemiPeakVo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "price")
@ToString(exclude = {"hostInfo", "roomInfo"})
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pri_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ri_idx", referencedColumnName = "ri_idx")
    private RoomInfo roomInfo = new RoomInfo();

    @Column(name = "regular_price", nullable = false)
    private int regularPrice;

    //성수기 가격 시작날짜 끝날짜
    @Embedded
    private PeakVo peakVo = new PeakVo();

    //비성수기 가격 시작날짜 끝날짜
    @Embedded
    private SemiPeakVo semi = new SemiPeakVo();

    @Column(name = "peak_set", nullable = false)
    private int peakSet;

    @Column(name = "pri_none", length = 100)
    private String priNone;
}