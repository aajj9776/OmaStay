package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPeakVo is a Querydsl query type for PeakVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPeakVo extends BeanPath<PeakVo> {

    private static final long serialVersionUID = -1681301973L;

    public static final QPeakVo peakVo = new QPeakVo("peakVo");

    public final DateTimePath<java.time.LocalDateTime> peakEnd = createDateTime("peakEnd", java.time.LocalDateTime.class);

    public final NumberPath<Integer> peakPrice = createNumber("peakPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> peakStart = createDateTime("peakStart", java.time.LocalDateTime.class);

    public QPeakVo(String variable) {
        super(PeakVo.class, forVariable(variable));
    }

    public QPeakVo(Path<? extends PeakVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPeakVo(PathMetadata metadata) {
        super(PeakVo.class, metadata);
    }

}

