package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.IcStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "issued_coupon")
@ToString(exclude = {"member", "coupon"})
public class IssuedCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ic_idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx", referencedColumnName = "mem_idx")
    private Member member = new Member();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cp_idx", referencedColumnName = "cp_idx")
    private Coupon coupon = new Coupon();

    //미사용 사용
    @Enumerated
    @Column(name = "ic_status", nullable = false)
    private IcStatus icStatus;

    @Column(name = "ic_code", nullable = false, length = 100)
    private String icCode;

    @Column(name = "ic_none", length = 100)
    private String icNone;
}