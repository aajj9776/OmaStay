package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor()
@Getter
@Setter
@Entity
@Table(name = "visitor")
@ToString
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "v_idx", nullable = false)
    private Integer id;

    @Column(name = "v_ip", nullable = false, length = 100)
    private String vIp;

    @Column(name = "v_date", nullable = false)
    private LocalDateTime vDate;

    @Column(name = "v_refer", nullable = false, length = 500)
    private String vRefer;

    @Column(name = "v_agent", nullable = false, length = 500)
    private String vAgent;

    @Column(name = "v_none", length = 100)
    private String vNone;
}