package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 1018277409L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public final QReservation reservation;

    public final StringPath revContent = createString("revContent");

    public final DateTimePath<java.time.LocalDateTime> revDate = createDateTime("revDate", java.time.LocalDateTime.class);

    public final com.omakase.omastay.vo.QFileImageNameVo revFileImageNameVo;

    public final StringPath revNone = createString("revNone");

    public final NumberPath<Float> revRating = createNumber("revRating", Float.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> revStatus = createEnum("revStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final StringPath revWriter = createString("revWriter");

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
        this.revFileImageNameVo = inits.isInitialized("revFileImageNameVo") ? new com.omakase.omastay.vo.QFileImageNameVo(forProperty("revFileImageNameVo")) : null;
    }

}

