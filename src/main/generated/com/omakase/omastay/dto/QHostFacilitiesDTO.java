package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QHostFacilitiesDTO is a Querydsl Projection type for HostFacilitiesDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QHostFacilitiesDTO extends ConstructorExpression<HostFacilitiesDTO> {

    private static final long serialVersionUID = -202324827L;

    public QHostFacilitiesDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> facilitiesId, com.querydsl.core.types.Expression<Integer> hostInfoId, com.querydsl.core.types.Expression<String> hostNone) {
        super(HostFacilitiesDTO.class, new Class<?>[]{int.class, int.class, int.class, String.class}, id, facilitiesId, hostInfoId, hostNone);
    }

}

