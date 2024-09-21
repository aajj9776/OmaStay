package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserProfileVo is a Querydsl query type for UserProfileVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserProfileVo extends BeanPath<UserProfileVo> {

    private static final long serialVersionUID = -651974364L;

    public static final QUserProfileVo userProfileVo = new QUserProfileVo("userProfileVo");

    public final StringPath email = createString("email");

    public final StringPath pw = createString("pw");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> status = createEnum("status", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public QUserProfileVo(String variable) {
        super(UserProfileVo.class, forVariable(variable));
    }

    public QUserProfileVo(Path<? extends UserProfileVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserProfileVo(PathMetadata metadata) {
        super(UserProfileVo.class, metadata);
    }

}

