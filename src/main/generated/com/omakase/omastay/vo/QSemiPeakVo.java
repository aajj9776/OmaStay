package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSemiPeakVo is a Querydsl query type for SemiPeakVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QSemiPeakVo extends BeanPath<SemiPeakVo> {

    private static final long serialVersionUID = 88735161L;

    public static final QSemiPeakVo semiPeakVo = new QSemiPeakVo("semiPeakVo");

    public final DateTimePath<java.time.LocalDateTime> semiEnd = createDateTime("semiEnd", java.time.LocalDateTime.class);

    public final NumberPath<Integer> semiPrice = createNumber("semiPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> semiStart = createDateTime("semiStart", java.time.LocalDateTime.class);

    public QSemiPeakVo(String variable) {
        super(SemiPeakVo.class, forVariable(variable));
    }

    public QSemiPeakVo(Path<? extends SemiPeakVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSemiPeakVo(PathMetadata metadata) {
        super(SemiPeakVo.class, metadata);
    }

}

