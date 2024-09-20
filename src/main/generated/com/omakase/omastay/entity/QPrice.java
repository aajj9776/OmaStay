package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPrice is a Querydsl query type for Price
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPrice extends EntityPathBase<Price> {

    private static final long serialVersionUID = -522814112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrice price = new QPrice("price");

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> peakSet = createNumber("peakSet", Integer.class);

    public final com.omakase.omastay.vo.QPeakVo peakVo;

    public final StringPath priNone = createString("priNone");

    public final NumberPath<Integer> regularPrice = createNumber("regularPrice", Integer.class);

    public final QRoomInfo roomInfo;

    public final com.omakase.omastay.vo.QSemiPeakVo semi;

    public QPrice(String variable) {
        this(Price.class, forVariable(variable), INITS);
    }

    public QPrice(Path<? extends Price> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrice(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrice(PathMetadata metadata, PathInits inits) {
        this(Price.class, metadata, inits);
    }

    public QPrice(Class<? extends Price> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
        this.peakVo = inits.isInitialized("peakVo") ? new com.omakase.omastay.vo.QPeakVo(forProperty("peakVo")) : null;
        this.roomInfo = inits.isInitialized("roomInfo") ? new QRoomInfo(forProperty("roomInfo"), inits.get("roomInfo")) : null;
        this.semi = inits.isInitialized("semi") ? new com.omakase.omastay.vo.QSemiPeakVo(forProperty("semi")) : null;
    }

}

