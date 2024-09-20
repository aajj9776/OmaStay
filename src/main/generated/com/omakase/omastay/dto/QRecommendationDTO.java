package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QRecommendationDTO is a Querydsl Projection type for RecommendationDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRecommendationDTO extends ConstructorExpression<RecommendationDTO> {

    private static final long serialVersionUID = 78991669L;

    public QRecommendationDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Float> recPoint, com.querydsl.core.types.Expression<String> recNone) {
        super(RecommendationDTO.class, new Class<?>[]{int.class, float.class, String.class}, id, recPoint, recNone);
    }

}

