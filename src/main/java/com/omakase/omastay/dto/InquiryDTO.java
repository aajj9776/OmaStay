package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Inquiry;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.CStatus;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.vo.FileImageNameVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InquiryDTO {
    private Integer id;
    private Integer mIdx;
    private String iqTitle;
    private String iqContent;
    private String iqWriter;
    private FileImageNameVo fileName = new FileImageNameVo();
    private LocalDateTime iqDate;
    private BooleanStatus iqStatus;
    private CStatus cStatus;
    private UserAuth iqAuth;
    private String iqNone;

    public InquiryDTO(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.mIdx = inquiry.getMember() != null ? inquiry.getMember().getId() : null;
        this.iqTitle = inquiry.getIqTitle();
        this.iqContent = inquiry.getIqContent();
        this.iqWriter = inquiry.getIqWriter();
        this.fileName = inquiry.getFileName();
        this.iqDate = inquiry.getIqDate();
        this.iqStatus = inquiry.getIqStatus();
        this.cStatus = inquiry.getCStatus();
        this.iqAuth = inquiry.getIqAuth();
        this.iqNone = inquiry.getIqNone();
    }

    @QueryProjection
    public InquiryDTO(Integer id, Integer mIdx, String iqTitle, String iqContent, String iqWriter, FileImageNameVo fileName, LocalDateTime iqDate, BooleanStatus iqStatus, CStatus cStatus, UserAuth iqAuth, String iqNone) {
        this.id = id;
        this.mIdx = mIdx;
        this.iqTitle = iqTitle;
        this.iqContent = iqContent;
        this.iqWriter = iqWriter;
        this.fileName = fileName;
        this.iqDate = iqDate;
        this.iqStatus = iqStatus;
        this.cStatus = cStatus;
        this.iqAuth = iqAuth;
        this.iqNone = iqNone;
    }
}