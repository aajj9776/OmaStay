package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHostFacilities is a Querydsl query type for HostFacilities
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHostFacilities extends EntityPathBase<HostFacilities> {

    private static final long serialVersionUID = -1288773614L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHostFacilities hostFacilities = new QHostFacilities("hostFacilities");

    public final QFacilities facilities;

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath roomNone = createString("roomNone");

    public QHostFacilities(String variable) {
        this(HostFacilities.class, forVariable(variable), INITS);
    }

    public QHostFacilities(Path<? extends HostFacilities> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHostFacilities(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHostFacilities(PathMetadata metadata, PathInits inits) {
        this(HostFacilities.class, metadata, inits);
    }

    public QHostFacilities(Class<? extends HostFacilities> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.facilities = inits.isInitialized("facilities") ? new QFacilities(forProperty("facilities")) : null;
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
    }

}

