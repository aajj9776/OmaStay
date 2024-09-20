package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPayment is a Querydsl query type for Payment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPayment extends EntityPathBase<Payment> {

    private static final long serialVersionUID = -384805795L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPayment payment = new QPayment("payment");

    public final StringPath cancelContent = createString("cancelContent");

    public final DateTimePath<java.time.LocalDateTime> cancelDate = createDateTime("cancelDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QIssuedCoupon issuedCoupon;

    public final StringPath nsalePrice = createString("nsalePrice");

    public final StringPath payContent = createString("payContent");

    public final DateTimePath<java.time.LocalDateTime> payDate = createDateTime("payDate", java.time.LocalDateTime.class);

    public final StringPath paymentKey = createString("paymentKey");

    public final EnumPath<com.omakase.omastay.entity.enumurate.PayMethod> payMethod = createEnum("payMethod", com.omakase.omastay.entity.enumurate.PayMethod.class);

    public final StringPath payNone = createString("payNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.PayStatus> payStatus = createEnum("payStatus", com.omakase.omastay.entity.enumurate.PayStatus.class);

    public final QPoint point;

    public final StringPath salePrice = createString("salePrice");

    public QPayment(String variable) {
        this(Payment.class, forVariable(variable), INITS);
    }

    public QPayment(Path<? extends Payment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPayment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPayment(PathMetadata metadata, PathInits inits) {
        this(Payment.class, metadata, inits);
    }

    public QPayment(Class<? extends Payment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.issuedCoupon = inits.isInitialized("issuedCoupon") ? new QIssuedCoupon(forProperty("issuedCoupon"), inits.get("issuedCoupon")) : null;
        this.point = inits.isInitialized("point") ? new QPoint(forProperty("point"), inits.get("point")) : null;
    }

}

