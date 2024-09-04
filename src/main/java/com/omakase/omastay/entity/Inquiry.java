package com.omakase.omastay.entity;

import com.omakase.omastay.vo.FileImageNameVo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.CStatus;
import com.omakase.omastay.entity.enumurate.UserAuth;
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
@Table(name = "inquiry")
@ToString(exclude = {"member"})
public class Inquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iq_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_idx" , referencedColumnName = "mem_idx")
    private Member member;

    @Column(name = "iq_title", nullable = false, length = 100)
    private String iqTitle;

    @Column(name = "iq_content", nullable = false, length = 500)
    private String iqContent;

    @Column(name = "iq_writer", nullable = false, length = 50)
    private String iqWriter;

    //파일이름, 파일원본이름
    @Embedded
    private FileImageNameVo fileName;

    @Column(name = "iq_date", nullable = false)
    private LocalDateTime iqDate;

    //FALSE:삭제, TRUE:존재
    @Enumerated
    @Column(name = "iq_status", nullable = false)
    private BooleanStatus iqStatus;

    @Enumerated
    @Column(name = "c_status", nullable = false)
    private CStatus cStatus;

    @Enumerated
    @Column(name = "iq_auth", nullable = false)
    private UserAuth iqAuth;

    @Column(name = "iq_none", length = 100)
    private String iqNone;
}