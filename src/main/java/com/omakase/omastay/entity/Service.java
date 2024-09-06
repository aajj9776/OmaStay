package com.omakase.omastay.entity;

import com.omakase.omastay.vo.FileImageNameVo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.entity.enumurate.SCate;
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
@Table(name = "service")
@ToString
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_idx", nullable = false)
    private int id;

    @Enumerated
    @Column(name = "s_cate", nullable = false)
    private SCate sCate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "s_auth", nullable = false)
    private UserAuth sAuth;

    @Column(name = "s_title", nullable = false, length = 100)
    private String sTitle;

    @Column(name = "s_content", nullable = false, length = 500)
    private String sContent;

    //파일이름, 파일원본이름
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fName", column = @Column(name = "s_fname", nullable = false, length = 500)),
            @AttributeOverride(name = "oName", column = @Column(name = "s_oname", nullable = false, length = 500))
    })
    private FileImageNameVo fileName = new FileImageNameVo();

    @Column(name = "s_date", nullable = false)
    private LocalDateTime sDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "s_status", nullable = false)
    private BooleanStatus sStatus;

    @Column(name = "s_none", length = 100)
    private String sNone;

}