package com.omakase.omastay.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.omakase.omastay.dto.QRoomInfoDTO is a Querydsl Projection type for RoomInfoDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRoomInfoDTO extends ConstructorExpression<RoomInfoDTO> {

    private static final long serialVersionUID = -876884475L;

    public QRoomInfoDTO(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<Integer> hIdx, com.querydsl.core.types.Expression<String> roomName, com.querydsl.core.types.Expression<String> roomType, com.querydsl.core.types.Expression<String> riIntro, com.querydsl.core.types.Expression<com.omakase.omastay.entity.enumurate.BooleanStatus> roomStatus, com.querydsl.core.types.Expression<String> roomNone, com.querydsl.core.types.Expression<Integer> roomPerson) {
        super(RoomInfoDTO.class, new Class<?>[]{int.class, int.class, String.class, String.class, String.class, com.omakase.omastay.entity.enumurate.BooleanStatus.class, String.class, int.class}, id, hIdx, roomName, roomType, riIntro, roomStatus, roomNone, roomPerson);
    }

}

