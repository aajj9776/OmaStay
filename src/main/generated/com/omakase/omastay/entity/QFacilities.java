package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFacilities is a Querydsl query type for Facilities
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacilities extends EntityPathBase<Facilities> {

    private static final long serialVersionUID = -1557836598L;

    public static final QFacilities facilities = new QFacilities("facilities");

    public final StringPath fCate = createString("fCate");

    public final StringPath fNone = createString("fNone");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QFacilities(String variable) {
        super(Facilities.class, forVariable(variable));
    }

    public QFacilities(Path<? extends Facilities> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFacilities(PathMetadata metadata) {
        super(Facilities.class, metadata);
    }

}

