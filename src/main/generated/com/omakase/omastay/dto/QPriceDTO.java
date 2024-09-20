package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QPriceDTO is a Querydsl Projection type for PriceDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPriceDTO extends ConstructorExpression<PriceDTO> {

    private static final long serialVersionUID = -1123449561L;

    public QPriceDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> hIdx, com.querydsl.core.types.Expression<Integer> riIdx, com.querydsl.core.types.Expression<Integer> regularPrice, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.PeakVo> peakVo, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.SemiPeakVo> semi, com.querydsl.core.types.Expression<Integer> peakSet, com.querydsl.core.types.Expression<String> priNone) {
        super(PriceDTO.class, new Class<?>[]{int.class, int.class, int.class, int.class, com.omakase.omastay.vo.PeakVo.class, com.omakase.omastay.vo.SemiPeakVo.class, int.class, String.class}, id, hIdx, riIdx, regularPrice, peakVo, semi, peakSet, priNone);
    }

}

