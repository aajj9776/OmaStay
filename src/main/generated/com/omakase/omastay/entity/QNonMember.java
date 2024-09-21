package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNonMember is a Querydsl query type for NonMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNonMember extends EntityPathBase<NonMember> {

    private static final long serialVersionUID = -275514274L;

    public static final QNonMember nonMember = new QNonMember("nonMember");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath nonEmail = createString("nonEmail");

    public final StringPath nonName = createString("nonName");

    public final StringPath nonNone = createString("nonNone");

    public final StringPath nonPhone = createString("nonPhone");

    public QNonMember(String variable) {
        super(NonMember.class, forVariable(variable));
    }

    public QNonMember(Path<? extends NonMember> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNonMember(PathMetadata metadata) {
        super(NonMember.class, metadata);
    }

}

