package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.BooleanStatus;
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
@Table(name = "review_comment")
@ToString(exclude = {"review"})
public class ReviewComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rc_idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rev_idx", referencedColumnName = "rev_idx")
    private Review review = new Review();

    @Column(name = "rc_comment", nullable = false, length = 100)
    private String rcComment;

    @Column(name = "rc_date", nullable = false)
    private LocalDateTime rcDate;

    //있음 삭제
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "rc_status", nullable = false)
    private BooleanStatus rcStatus;

    @Column(name = "rc_none", length = 100)
    private String rcNone;
}