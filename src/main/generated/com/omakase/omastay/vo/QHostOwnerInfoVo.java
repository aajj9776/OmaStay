package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHostOwnerInfoVo is a Querydsl query type for HostOwnerInfoVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHostOwnerInfoVo extends BeanPath<HostOwnerInfoVo> {

    private static final long serialVersionUID = -149320449L;

    public static final QHostOwnerInfoVo hostOwnerInfoVo = new QHostOwnerInfoVo("hostOwnerInfoVo");

    public final StringPath hintro = createString("hintro");

    public final StringPath hostName = createString("hostName");

    public QHostOwnerInfoVo(String variable) {
        super(HostOwnerInfoVo.class, forVariable(variable));
    }

    public QHostOwnerInfoVo(Path<? extends HostOwnerInfoVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHostOwnerInfoVo(PathMetadata metadata) {
        super(HostOwnerInfoVo.class, metadata);
    }

}

