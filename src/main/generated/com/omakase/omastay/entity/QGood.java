package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGood is a Querydsl query type for Good
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGood extends EntityPathBase<Good> {

    private static final long serialVersionUID = -432777786L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGood good = new QGood("good");

    public final DateTimePath<java.time.LocalDateTime> goodDate = createDateTime("goodDate", java.time.LocalDateTime.class);

    public final StringPath goodNone = createString("goodNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> goodStatus = createEnum("goodStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final QReview review;

    public QGood(String variable) {
        this(Good.class, forVariable(variable), INITS);
    }

    public QGood(Path<? extends Good> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGood(PathMetadata metadata, PathInits inits) {
        this(Good.class, metadata, inits);
    }

    public QGood(Class<? extends Good> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

