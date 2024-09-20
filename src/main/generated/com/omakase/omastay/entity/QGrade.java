package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGrade is a Querydsl query type for Grade
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGrade extends EntityPathBase<Grade> {

    private static final long serialVersionUID = -531133458L;

    public static final QGrade grade = new QGrade("grade");

    public final StringPath gCate = createString("gCate");

    public final StringPath gNone = createString("gNone");

    public final StringPath gPoint = createString("gPoint");

    public final StringPath gReq = createString("gReq");

    public final StringPath gSale = createString("gSale");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QGrade(String variable) {
        super(Grade.class, forVariable(variable));
    }

    public QGrade(Path<? extends Grade> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGrade(PathMetadata metadata) {
        super(Grade.class, metadata);
    }

}

