package com.omakase.omastay.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Getter
@Setter
public class AddressVo {
    @Column(name = "h_post_code", nullable = false, length = 200)
    private String postCode;

    @Column(name = "h_street", nullable = false, length = 200)
    private String street;

    @Column(name = "h_detail", length = 200)
    private String detail;
}
