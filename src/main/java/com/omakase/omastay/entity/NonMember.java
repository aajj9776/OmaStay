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
@Table(name = "non_member")
@ToString
public class NonMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "non_idx", nullable = false)
    private Integer id;

    @Column(name = "non_name", nullable = false, length = 100, updatable = false)
    private String nonName;

    @Column(name = "non_phone", nullable = false, length = 100, updatable = false)
    private String nonPhone;

    @Column(name = "non_email", nullable = false, length = 200, updatable = false)
    private String nonEmail;

    @Column(name = "non_none", length = 100)
    private String nonNone;
}