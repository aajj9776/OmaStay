package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHostContactInfoVo is a Querydsl query type for HostContactInfoVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QHostContactInfoVo extends BeanPath<HostContactInfoVo> {

    private static final long serialVersionUID = 1002156908L;

    public static final QHostContactInfoVo hostContactInfoVo = new QHostContactInfoVo("hostContactInfoVo");

    public final StringPath contactEmail = createString("contactEmail");

    public final StringPath contactName = createString("contactName");

    public QHostContactInfoVo(String variable) {
        super(HostContactInfoVo.class, forVariable(variable));
    }

    public QHostContactInfoVo(Path<? extends HostContactInfoVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHostContactInfoVo(PathMetadata metadata) {
        super(HostContactInfoVo.class, metadata);
    }

}

