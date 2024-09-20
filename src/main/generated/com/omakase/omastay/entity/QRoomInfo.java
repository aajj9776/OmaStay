package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomInfo is a Querydsl query type for RoomInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoomInfo extends EntityPathBase<RoomInfo> {

    private static final long serialVersionUID = -600400718L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomInfo roomInfo = new QRoomInfo("roomInfo");

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath roomIntro = createString("roomIntro");

    public final StringPath roomName = createString("roomName");

    public final StringPath roomNone = createString("roomNone");

    public final NumberPath<Integer> roomPerson = createNumber("roomPerson", Integer.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> roomStatus = createEnum("roomStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final StringPath roomType = createString("roomType");

    public QRoomInfo(String variable) {
        this(RoomInfo.class, forVariable(variable), INITS);
    }

    public QRoomInfo(Path<? extends RoomInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomInfo(PathMetadata metadata, PathInits inits) {
        this(RoomInfo.class, metadata, inits);
    }

    public QRoomInfo(Class<? extends RoomInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
    }

}

