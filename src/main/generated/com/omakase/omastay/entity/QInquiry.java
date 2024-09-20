package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiry is a Querydsl query type for Inquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiry extends EntityPathBase<Inquiry> {

    private static final long serialVersionUID = -1937331170L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiry inquiry = new QInquiry("inquiry");

    public final EnumPath<com.omakase.omastay.entity.enumurate.CStatus> cStatus = createEnum("cStatus", com.omakase.omastay.entity.enumurate.CStatus.class);

    public final com.omakase.omastay.vo.QFileImageNameVo fileName;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.UserAuth> iqAuth = createEnum("iqAuth", com.omakase.omastay.entity.enumurate.UserAuth.class);

    public final StringPath iqContent = createString("iqContent");

    public final DateTimePath<java.time.LocalDateTime> iqDate = createDateTime("iqDate", java.time.LocalDateTime.class);

    public final StringPath iqNone = createString("iqNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> iqStatus = createEnum("iqStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final StringPath iqTitle = createString("iqTitle");

    public final StringPath iqWriter = createString("iqWriter");

    public final QMember member;

    public QInquiry(String variable) {
        this(Inquiry.class, forVariable(variable), INITS);
    }

    public QInquiry(Path<? extends Inquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiry(PathMetadata metadata, PathInits inits) {
        this(Inquiry.class, metadata, inits);
    }

    public QInquiry(Class<? extends Inquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileName = inits.isInitialized("fileName") ? new com.omakase.omastay.vo.QFileImageNameVo(forProperty("fileName")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

