package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.vo.FileImageNameVo;

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
@Table(name = "review")
@ToString(exclude = {"member", "reservation", "hostInfo"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx", referencedColumnName = "mem_idx")
    private Member member = new Member();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_idx", referencedColumnName = "res_idx")
    private Reservation reservation = new Reservation();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Column(name = "rev_writer", nullable = false, length = 100)
    private String revWriter;

    @Column(name = "rev_content", nullable = false, length = 500)
    private String revContent;

    @Column(name = "rev_date", nullable = false)
    private LocalDateTime revDate;

    // 있음 삭제
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rev_status", nullable = false)
    private BooleanStatus revStatus;
    
    @Column(name = "rev_none", length = 100)
    private String revNone;
    
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fName", column = @Column(name = "rev_fname", length = 200)),
            @AttributeOverride(name = "oName", column = @Column(name = "rev_oname", length = 200))
    })
    private FileImageNameVo revFileImageNameVo = new FileImageNameVo();

    @Column(name = "rev_ rating")
    private Float revRating;




}

