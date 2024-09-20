package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHostInfo is a Querydsl query type for HostInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHostInfo extends EntityPathBase<HostInfo> {

    private static final long serialVersionUID = -727653633L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHostInfo hostInfo = new QHostInfo("hostInfo");

    public final QAdminMember adminMember;

    public final StringPath checkin = createString("checkin");

    public final StringPath checkout = createString("checkout");

    public final StringPath directions = createString("directions");

    public final EnumPath<com.omakase.omastay.entity.enumurate.HCate> hCate = createEnum("hCate", com.omakase.omastay.entity.enumurate.HCate.class);

    public final StringPath hname = createString("hname");

    public final StringPath hNone = createString("hNone");

    public final com.omakase.omastay.vo.QAddressVo hostAddress;

    public final com.omakase.omastay.vo.QHostContactInfoVo hostContactInfo;

    public final com.omakase.omastay.vo.QHostOwnerInfoVo hostOwnerInfo;

    public final StringPath hphone = createString("hphone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.HStatus> hStatus = createEnum("hStatus", com.omakase.omastay.entity.enumurate.HStatus.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.HStep> hStep = createEnum("hStep", com.omakase.omastay.entity.enumurate.HStep.class);

    public final StringPath hurl = createString("hurl");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath priceAdd = createString("priceAdd");

    public final StringPath region = createString("region");

    public final StringPath rules = createString("rules");

    public final StringPath xAxis = createString("xAxis");

    public final StringPath yAxis = createString("yAxis");

    public QHostInfo(String variable) {
        this(HostInfo.class, forVariable(variable), INITS);
    }

    public QHostInfo(Path<? extends HostInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHostInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHostInfo(PathMetadata metadata, PathInits inits) {
        this(HostInfo.class, metadata, inits);
    }

    public QHostInfo(Class<? extends HostInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminMember = inits.isInitialized("adminMember") ? new QAdminMember(forProperty("adminMember"), inits.get("adminMember")) : null;
        this.hostAddress = inits.isInitialized("hostAddress") ? new com.omakase.omastay.vo.QAddressVo(forProperty("hostAddress")) : null;
        this.hostContactInfo = inits.isInitialized("hostContactInfo") ? new com.omakase.omastay.vo.QHostContactInfoVo(forProperty("hostContactInfo")) : null;
        this.hostOwnerInfo = inits.isInitialized("hostOwnerInfo") ? new com.omakase.omastay.vo.QHostOwnerInfoVo(forProperty("hostOwnerInfo")) : null;
    }

}

