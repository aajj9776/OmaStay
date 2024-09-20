package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewComment is a Querydsl query type for ReviewComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewComment extends EntityPathBase<ReviewComment> {

    private static final long serialVersionUID = 1452531966L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewComment reviewComment = new QReviewComment("reviewComment");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath rcComment = createString("rcComment");

    public final DateTimePath<java.time.LocalDateTime> rcDate = createDateTime("rcDate", java.time.LocalDateTime.class);

    public final StringPath rcNone = createString("rcNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> rcStatus = createEnum("rcStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final QReview review;

    public QReviewComment(String variable) {
        this(ReviewComment.class, forVariable(variable), INITS);
    }

    public QReviewComment(Path<? extends ReviewComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewComment(PathMetadata metadata, PathInits inits) {
        this(ReviewComment.class, metadata, inits);
    }

    public QReviewComment(Class<? extends ReviewComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.review = inits.isInitialized("review") ? new QReview(forProperty("review"), inits.get("review")) : null;
    }

}

