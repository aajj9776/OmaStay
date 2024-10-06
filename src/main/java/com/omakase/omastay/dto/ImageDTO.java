package com.omakase.omastay.dto;

import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.vo.FileImageNameVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ImageDTO {
    private Integer id;
    private Integer rId;
    private Integer hIdx;
    private ImgCate imgCate;
    private FileImageNameVo imgName = new FileImageNameVo();
    private BooleanStatus imgStatus;
    private String imgNone;

    public ImageDTO(Image image) {
        this.id = image.getId();
        this.rId = image.getRoomInfo() != null ? image.getRoomInfo().getId() : null;
        this.hIdx = image.getHostInfo() != null ? image.getHostInfo().getId() : null;
        this.imgCate = image.getImgCate();
        this.imgName = image.getImgName();
        this.imgStatus = image.getImgStatus();
        this.imgNone = image.getImgNone();
    }

    @QueryProjection
    public ImageDTO(Integer id, Integer rId, Integer hIdx, ImgCate imgCate, FileImageNameVo imgName, BooleanStatus imgStatus, String imgNone) {
        this.id = id;
        this.rId = rId;
        this.hIdx = hIdx;
        this.imgCate = imgCate;
        this.imgName = imgName;
        this.imgStatus = imgStatus;
        this.imgNone = imgNone;
    }
}