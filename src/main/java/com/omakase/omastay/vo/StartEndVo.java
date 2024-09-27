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
public class StartEndVo {
    @Column(name = "res_start", nullable = false)
    private LocalDateTime start;

    @Column(name = "res_end", nullable = false)
    private LocalDateTime end;
}
