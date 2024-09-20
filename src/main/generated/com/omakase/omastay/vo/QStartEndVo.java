package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStartEndVo is a Querydsl query type for StartEndVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStartEndVo extends BeanPath<StartEndVo> {

    private static final long serialVersionUID = -293089691L;

    public static final QStartEndVo startEndVo = new QStartEndVo("startEndVo");

    public final DateTimePath<java.time.LocalDateTime> end = createDateTime("end", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> start = createDateTime("start", java.time.LocalDateTime.class);

    public QStartEndVo(String variable) {
        super(StartEndVo.class, forVariable(variable));
    }

    public QStartEndVo(Path<? extends StartEndVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStartEndVo(PathMetadata metadata) {
        super(StartEndVo.class, metadata);
    }

}

