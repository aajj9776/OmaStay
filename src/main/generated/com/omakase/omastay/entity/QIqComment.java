package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIqComment is a Querydsl query type for IqComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIqComment extends EntityPathBase<IqComment> {

    private static final long serialVersionUID = 835306670L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIqComment iqComment = new QIqComment("iqComment");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QInquiry inquiry;

    public final StringPath iqcContent = createString("iqcContent");

    public final DateTimePath<java.time.LocalDateTime> iqcDate = createDateTime("iqcDate", java.time.LocalDateTime.class);

    public final StringPath iqcNone = createString("iqcNone");

    public final StringPath iqcTitle = createString("iqcTitle");

    public QIqComment(String variable) {
        this(IqComment.class, forVariable(variable), INITS);
    }

    public QIqComment(Path<? extends IqComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIqComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIqComment(PathMetadata metadata, PathInits inits) {
        this(IqComment.class, metadata, inits);
    }

    public QIqComment(Class<? extends IqComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new QInquiry(forProperty("inquiry"), inits.get("inquiry")) : null;
    }

}

