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
public class FileImageNameVo {

    @Column(name = "iq_oname", nullable = false , length = 500)
    private String oName;

    @Column(name = "iq_fname", nullable = false , length = 500)
    private String fName;
}
