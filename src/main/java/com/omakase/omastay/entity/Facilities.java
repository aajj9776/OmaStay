package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facilities")
@ToString
public class Facilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_idx", nullable = false)
    private Integer id;

    @Column(name = "f_cate", nullable = false, length = 100)
    private String fCate;

    @Column(name = "f_none", length = 100)
    private String fNone;

}