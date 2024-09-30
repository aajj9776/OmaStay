package com.omakase.omastay.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SemiPeakVo {

    @Column(name = "semi_start")
    private LocalDateTime semiStart;

    @Column(name = "semi_end")
    private LocalDateTime semiEnd;

    @Column(name = "semi_price")
    private Integer semiPrice;
}
