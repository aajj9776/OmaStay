package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Service;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.vo.FileImageNameVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ServiceDTO {
    private Integer id;
    private SCate sCate;
    private UserAuth sAuth;
    private String sTitle;
    private String sContent;
    private FileImageNameVo fileName = new FileImageNameVo();
    private LocalDateTime sDate;
    private BooleanStatus sStatus;
    private String sNone;

    public ServiceDTO(Service service) {
        this.id = service.getId();
        this.sCate = service.getSCate();
        this.sAuth = service.getSAuth();
        this.sTitle = service.getSTitle();
        this.sContent = service.getSContent();
        this.fileName = service.getFileName();
        this.sDate = service.getSDate();
        this.sStatus = service.getSStatus();
        this.sNone = service.getSNone();
    }

    @QueryProjection
    public ServiceDTO(Integer id, SCate sCate, UserAuth sAuth, String sTitle, String sContent, FileImageNameVo fileName,
                      LocalDateTime sDate, BooleanStatus sStatus, String sNone) {
        this.id = id;
        this.sCate = sCate;
        this.sAuth = sAuth;
        this.sTitle = sTitle;
        this.sContent = sContent;
        this.fileName = fileName;
        this.sDate = sDate;
        this.sStatus = sStatus;
        this.sNone = sNone;
    }
}