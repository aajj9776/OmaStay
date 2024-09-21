package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAddressVo is a Querydsl query type for AddressVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAddressVo extends BeanPath<AddressVo> {

    private static final long serialVersionUID = -1577235782L;

    public static final QAddressVo addressVo = new QAddressVo("addressVo");

    public final StringPath detail = createString("detail");

    public final StringPath postCode = createString("postCode");

    public final StringPath street = createString("street");

    public QAddressVo(String variable) {
        super(AddressVo.class, forVariable(variable));
    }

    public QAddressVo(Path<? extends AddressVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAddressVo(PathMetadata metadata) {
        super(AddressVo.class, metadata);
    }

}

