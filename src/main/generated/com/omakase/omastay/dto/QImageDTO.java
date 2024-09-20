package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QImageDTO is a Querydsl Projection type for ImageDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QImageDTO extends ConstructorExpression<ImageDTO> {

    private static final long serialVersionUID = -806110251L;

    public QImageDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> rId, com.querydsl.core.types.Expression<Integer> hIdx, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.ImgCate> imgCate, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.FileImageNameVo> imgName, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> imgStatus, com.querydsl.core.types.Expression<String> imgNone) {
        super(ImageDTO.class, new Class<?>[]{int.class, int.class, int.class, com.omakase.omastay.entity.enumurate.ImgCate.class, com.omakase.omastay.vo.FileImageNameVo.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, String.class}, id, rId, hIdx, imgCate, imgName, imgStatus, imgNone);
    }

}

