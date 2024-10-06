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
public class PeakVo {
    @Column(name = "peak_start")
    private LocalDateTime peakStart;

    @Column(name = "peak_end")
    private LocalDateTime peakEnd;

    @Column(name = "peak_price")
    private Integer peakPrice;
}
