package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -2068684125L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final QNonMember nonMember;

    public final QPayment payment;

    public final StringPath resEmail = createString("resEmail");

    public final StringPath resName = createString("resName");

    public final StringPath resNone = createString("resNone");

    public final StringPath resNum = createString("resNum");

    public final NumberPath<Integer> resPerson = createNumber("resPerson", Integer.class);

    public final NumberPath<Integer> resPrice = createNumber("resPrice", Integer.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.ResStatus> resStatus = createEnum("resStatus", com.omakase.omastay.entity.enumurate.ResStatus.class);

    public final QRoomInfo roomInfo;

    public final com.omakase.omastay.vo.QStartEndVo startEndVo;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.nonMember = inits.isInitialized("nonMember") ? new QNonMember(forProperty("nonMember")) : null;
        this.payment = inits.isInitialized("payment") ? new QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.roomInfo = inits.isInitialized("roomInfo") ? new QRoomInfo(forProperty("roomInfo"), inits.get("roomInfo")) : null;
        this.startEndVo = inits.isInitialized("startEndVo") ? new com.omakase.omastay.vo.QStartEndVo(forProperty("startEndVo")) : null;
    }

}

