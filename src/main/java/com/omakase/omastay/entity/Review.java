package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.BooleanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review")
@ToString(exclude = {"member", "reservation"})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx", referencedColumnName = "mem_idx")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_idx", referencedColumnName = "res_idx")
    private Reservation reservation;

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

    @Column(name = "`rev_ rating`")
    private Float revRating;

    @Column(name = "rev_none", length = 100)
    private String revNone;
}