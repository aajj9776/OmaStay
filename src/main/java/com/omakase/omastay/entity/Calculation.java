package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.CalStatus;
import com.omakase.omastay.vo.StartEndVo;
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
@Table(name = "calculation")
@ToString(exclude = "hostInfo")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cal_idx", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Column(name = "cal_amount", nullable = false)
    private Integer calAmount;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "cal_status", nullable = false)
    private CalStatus calStatus;

    @Column(name = "cal_month", nullable = false)
    private LocalDateTime calMonth;

    @Column(name = "cal_reg_time")
    private LocalDateTime calRegTime;

    @Column(name = "cal_confirm_time")
    private LocalDateTime calConfirmTime;

    @Column(name = "cal_complete_time")
    private LocalDateTime calCompleteTime;

    @Column(name = "cal_none", length = 100)
    private String calNone;

}