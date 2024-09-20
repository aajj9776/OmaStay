package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSales is a Querydsl query type for Sales
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSales extends EntityPathBase<Sales> {

    private static final long serialVersionUID = -520547037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSales sales = new QSales("sales");

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final QReservation reservation;

    public final DatePath<java.time.LocalDate> salDate = createDate("salDate", java.time.LocalDate.class);

    public final StringPath salNone = createString("salNone");

    public QSales(String variable) {
        this(Sales.class, forVariable(variable), INITS);
    }

    public QSales(Path<? extends Sales> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSales(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSales(PathMetadata metadata, PathInits inits) {
        this(Sales.class, metadata, inits);
    }

    public QSales(Class<? extends Sales> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
    }

}

