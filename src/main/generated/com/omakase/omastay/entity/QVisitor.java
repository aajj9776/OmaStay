package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVisitor is a Querydsl query type for Visitor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVisitor extends EntityPathBase<Visitor> {

    private static final long serialVersionUID = 868636357L;

    public static final QVisitor visitor = new QVisitor("visitor");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath vAgent = createString("vAgent");

    public final DateTimePath<java.time.LocalDateTime> vDate = createDateTime("vDate", java.time.LocalDateTime.class);

    public final StringPath vIp = createString("vIp");

    public final StringPath vNone = createString("vNone");

    public final StringPath vRefer = createString("vRefer");

    public QVisitor(String variable) {
        super(Visitor.class, forVariable(variable));
    }

    public QVisitor(Path<? extends Visitor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVisitor(PathMetadata metadata) {
        super(Visitor.class, metadata);
    }

}

