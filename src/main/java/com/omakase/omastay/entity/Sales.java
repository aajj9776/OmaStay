package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
@ToString(exclude = {"hostInfo", "reservation"})
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sal_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "res_idx", referencedColumnName = "res_idx")
    private Reservation reservation;

    @Column(name = "sal_date", nullable = false)
    private LocalDate salDate;

    @Column(name = "sal_none", length = 100)
    private String salNone;

}