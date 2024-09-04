package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.PayMethod;
import com.omakase.omastay.entity.enumurate.PayStatus;
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
@Table(name = "payment")
@ToString(exclude = {"issuedCoupon", "point"})
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_idx", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ic_idx", referencedColumnName = "ic_idx", nullable = true)
    private IssuedCoupon issuedCoupon;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_idx", referencedColumnName = "p_idx", nullable = true)
    private Point point;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "pay_status", nullable = false)
    private PayStatus payStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_method", nullable = false, length = 100)
    private PayMethod payMethod;

    @Column(name = "pay_content", nullable = false, length = 500)
    private String payContent;

    @Column(name = "sale_price", length = 100)
    private String salePrice;

    @Column(name = "nsale_price", length = 100)
    private String nsalePrice;

    @Column(name = "cancel_time", length = 100)
    private String cancelTime;

    @Column(name = "cancel_content", length = 100)
    private String cancelContent;

    @Column(name = "pay_date", nullable = false)
    private LocalDateTime payDate;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "pay_none", length = 100)
    private String payNone;

}