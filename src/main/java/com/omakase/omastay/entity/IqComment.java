package com.omakase.omastay.entity;

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
@Table(name = "iq_comment")
@ToString(exclude = {"inquiry"})
public class IqComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iqc_idx", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iq_idx", referencedColumnName = "iq_idx")
    private Inquiry inquiry = new Inquiry();

    @Column(name = "iqc_title", nullable = false, length = 100)
    private String iqcTitle;

    @Column(name = "iqc_content", nullable = false, length = 500)
    private String iqcContent;

    @Column(name = "iqc_date", nullable = false)
    private LocalDateTime iqcDate;

    @Column(name = "iqc_none", length = 100)
    private String iqcNone;

    //영속성 시간 자동추가
    @PrePersist
    protected void Now() {
        this.iqcDate = LocalDateTime.now();
    }

}