package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAdminMember is a Querydsl query type for AdminMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdminMember extends EntityPathBase<AdminMember> {

    private static final long serialVersionUID = -1178033760L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAdminMember adminMember = new QAdminMember("adminMember");

    public final NumberPath<Integer> adAuth = createNumber("adAuth", Integer.class);

    public final StringPath adId = createString("adId");

    public final com.omakase.omastay.vo.QUserProfileVo adminProfile;

    public final StringPath adNone = createString("adNone");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QAdminMember(String variable) {
        this(AdminMember.class, forVariable(variable), INITS);
    }

    public QAdminMember(Path<? extends AdminMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAdminMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAdminMember(PathMetadata metadata, PathInits inits) {
        this(AdminMember.class, metadata, inits);
    }

    public QAdminMember(Class<? extends AdminMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.adminProfile = inits.isInitialized("adminProfile") ? new com.omakase.omastay.vo.QUserProfileVo(forProperty("adminProfile")) : null;
    }

}

