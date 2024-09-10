package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "point")
@ToString(exclude = {"member"})
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx", referencedColumnName = "mem_idx")
    private Member member = new Member();

    @Column(name = "p_sum", nullable = false)
    private Integer pSum;

    @Column(name = "p_value", nullable = false)
    private Integer pValue;

    @Column(name = "p_date", nullable = false)
    private LocalDateTime pDate;

    @Column(name = "p_none", length = 100)
    private String pNone;

    //영속성 시간 자동추가
    @PrePersist
    public void prePersist() {
        pDate = LocalDateTime.now();
    }

}