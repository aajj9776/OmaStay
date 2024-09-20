package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QService is a Querydsl query type for Service
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QService extends EntityPathBase<Service> {

    private static final long serialVersionUID = -1908938484L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QService service = new QService("service");

    public final com.omakase.omastay.vo.QFileImageNameVo fileName;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.UserAuth> sAuth = createEnum("sAuth", com.omakase.omastay.entity.enumurate.UserAuth.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.SCate> sCate = createEnum("sCate", com.omakase.omastay.entity.enumurate.SCate.class);

    public final StringPath sContent = createString("sContent");

    public final DateTimePath<java.time.LocalDateTime> sDate = createDateTime("sDate", java.time.LocalDateTime.class);

    public final StringPath sNone = createString("sNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> sStatus = createEnum("sStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final StringPath sTitle = createString("sTitle");

    public QService(String variable) {
        this(Service.class, forVariable(variable), INITS);
    }

    public QService(Path<? extends Service> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QService(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QService(PathMetadata metadata, PathInits inits) {
        this(Service.class, metadata, inits);
    }

    public QService(Class<? extends Service> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fileName = inits.isInitialized("fileName") ? new com.omakase.omastay.vo.QFileImageNameVo(forProperty("fileName")) : null;
    }

}

