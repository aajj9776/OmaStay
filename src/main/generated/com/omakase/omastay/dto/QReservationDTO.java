package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QReservationDTO is a Querydsl Projection type for ReservationDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QReservationDTO extends ConstructorExpression<ReservationDTO> {

    private static final long serialVersionUID = -566411516L;

    public QReservationDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> roomIdx, com.querydsl.core.types.Expression<Integer> memIdx, com.querydsl.core.types.Expression<Integer> nonIdx, com.querydsl.core.types.Expression<Integer> paymentId, com.querydsl.core.types.Expression<String> resNum, com.querydsl.core.types.Expression<? extends com.omakase.omastay.vo.StartEndVo> startEndVo, com.querydsl.core.types.Expression<String> resName, com.querydsl.core.types.Expression<String> resEmail, com.querydsl.core.types.Expression<Integer> resPerson, com.querydsl.core.types.Expression<Integer> resPrice, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.ResStatus> resStatus, com.querydsl.core.types.Expression<String> resNone) {
        super(ReservationDTO.class, new Class<?>[]{int.class, int.class, int.class, int.class, int.class, String.class, com.omakase.omastay.vo.StartEndVo.class, String.class, String.class, int.class, int.class, com.omakase.omastay.entity.enumurate.ResStatus.class, String.class}, id, roomIdx, memIdx, nonIdx, paymentId, resNum, startEndVo, resName, resEmail, resPerson, resPrice, resStatus, resNone);
    }

}

