package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = 598052591L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final EnumPath<com.omakase.omastay.entity.enumurate.CpCate> cpCate = createEnum("cpCate", com.omakase.omastay.entity.enumurate.CpCate.class);

    public final StringPath cpContent = createString("cpContent");

    public final EnumPath<com.omakase.omastay.entity.enumurate.CpMethod> cpMethod = createEnum("cpMethod", com.omakase.omastay.entity.enumurate.CpMethod.class);

    public final StringPath cpNone = createString("cpNone");

    public final StringPath cpSale = createString("cpSale");

    public final com.omakase.omastay.vo.QStartEndVo cpStartEnd;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public QCoupon(String variable) {
        this(Coupon.class, forVariable(variable), INITS);
    }

    public QCoupon(Path<? extends Coupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoupon(PathMetadata metadata, PathInits inits) {
        this(Coupon.class, metadata, inits);
    }

    public QCoupon(Class<? extends Coupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cpStartEnd = inits.isInitialized("cpStartEnd") ? new com.omakase.omastay.vo.QStartEndVo(forProperty("cpStartEnd")) : null;
    }

}

