package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QIssuedCoupon is a Querydsl query type for IssuedCoupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIssuedCoupon extends EntityPathBase<IssuedCoupon> {

    private static final long serialVersionUID = 51513018L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QIssuedCoupon issuedCoupon = new QIssuedCoupon("issuedCoupon");

    public final QCoupon coupon;

    public final StringPath icCode = createString("icCode");

    public final StringPath icNone = createString("icNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.IcStatus> icStatus = createEnum("icStatus", com.omakase.omastay.entity.enumurate.IcStatus.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QMember member;

    public QIssuedCoupon(String variable) {
        this(IssuedCoupon.class, forVariable(variable), INITS);
    }

    public QIssuedCoupon(Path<? extends IssuedCoupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QIssuedCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QIssuedCoupon(PathMetadata metadata, PathInits inits) {
        this(IssuedCoupon.class, metadata, inits);
    }

    public QIssuedCoupon(Class<? extends IssuedCoupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.coupon = inits.isInitialized("coupon") ? new QCoupon(forProperty("coupon"), inits.get("coupon")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

