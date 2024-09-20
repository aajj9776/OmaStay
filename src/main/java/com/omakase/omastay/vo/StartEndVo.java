package com.omakase.omastay.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.omakase.omastay.config.CustomLocalDateTimeDeserializer;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StartEndVo {
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "res_start", nullable = false)
    private LocalDateTime start;

    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "res_end", nullable = false)
    private LocalDateTime end;
}
