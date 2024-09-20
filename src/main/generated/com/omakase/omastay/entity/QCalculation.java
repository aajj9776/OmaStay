package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCalculation is a Querydsl query type for Calculation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCalculation extends EntityPathBase<Calculation> {

    private static final long serialVersionUID = -1440285280L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCalculation calculation = new QCalculation("calculation");

    public final NumberPath<Integer> calAmount = createNumber("calAmount", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> calCancelTime = createDateTime("calCancelTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> calCompleteTime = createDateTime("calCompleteTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> calConfirmTime = createDateTime("calConfirmTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> calLegTime = createDateTime("calLegTime", java.time.LocalDateTime.class);

    public final StringPath calNone = createString("calNone");

    public final com.omakase.omastay.vo.QStartEndVo calStartEnd;

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> calStatus = createEnum("calStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCalculation(String variable) {
        this(Calculation.class, forVariable(variable), INITS);
    }

    public QCalculation(Path<? extends Calculation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCalculation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCalculation(PathMetadata metadata, PathInits inits) {
        this(Calculation.class, metadata, inits);
    }

    public QCalculation(Class<? extends Calculation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.calStartEnd = inits.isInitialized("calStartEnd") ? new com.omakase.omastay.vo.QStartEndVo(forProperty("calStartEnd")) : null;
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
    }

}

