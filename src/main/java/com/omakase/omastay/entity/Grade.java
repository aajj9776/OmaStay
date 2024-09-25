package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grade")
@ToString
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "g_idx", nullable = false)
    private Integer id;

    @Column(name = "g_cate", nullable = false, length = 100)
    private String gCate;

    @Column(name = "g_req", nullable = false, length = 100)
    private String gReq;

    @Column(name = "g_sale", nullable = false, length = 100)
    private String gSale;

    @Column(name = "g_point", nullable = false, length = 100)
    private String gPoint;

    @Column(name = "g_none", length = 100)
    private String gNone;
}