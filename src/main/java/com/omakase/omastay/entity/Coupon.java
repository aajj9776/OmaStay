package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.CpCate;
import com.omakase.omastay.entity.enumurate.CpMethod;
import com.omakase.omastay.vo.StartEndVo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "coupon")
@ToString
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cp_idx", nullable = false, length = 100)
    private int id;

    @Column(name = "cp_content", length = 100)
    private String cpContent;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "start", column = @Column(name = "cp_date", nullable = false)),
            @AttributeOverride(name = "end", column = @Column(name = "cp_endtime", nullable = false))
    })
    private StartEndVo cpStartEnd;

    @Column(name = "cp_sale", nullable = false, length = 100)
    private String cpSale;

    @Enumerated
    @Column(name = "cp_cate", nullable = false)
    private CpCate cpCate;

    @Enumerated
    @Column(name = "cp_method", nullable = false)
    private CpMethod cpMethod;

    @Column(name = "cp_none", length = 100)
    private String cpNone;

}