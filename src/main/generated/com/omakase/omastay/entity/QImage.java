package com.omakase.omastay.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImage is a Querydsl query type for Image
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImage extends EntityPathBase<Image> {

    private static final long serialVersionUID = -529435278L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImage image = new QImage("image");

    public final QHostInfo hostInfo;

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final EnumPath<com.omakase.omastay.entity.enumurate.ImgCate> imgCate = createEnum("imgCate", com.omakase.omastay.entity.enumurate.ImgCate.class);

    public final com.omakase.omastay.vo.QFileImageNameVo imgName;

    public final StringPath imgNone = createString("imgNone");

    public final EnumPath<com.omakase.omastay.entity.enumurate.BooleanStatus> imgStatus = createEnum("imgStatus", com.omakase.omastay.entity.enumurate.BooleanStatus.class);

    public final QRoomInfo roomInfo;

    public QImage(String variable) {
        this(Image.class, forVariable(variable), INITS);
    }

    public QImage(Path<? extends Image> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImage(PathMetadata metadata, PathInits inits) {
        this(Image.class, metadata, inits);
    }

    public QImage(Class<? extends Image> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hostInfo = inits.isInitialized("hostInfo") ? new QHostInfo(forProperty("hostInfo"), inits.get("hostInfo")) : null;
        this.imgName = inits.isInitialized("imgName") ? new com.omakase.omastay.vo.QFileImageNameVo(forProperty("imgName")) : null;
        this.roomInfo = inits.isInitialized("roomInfo") ? new QRoomInfo(forProperty("roomInfo"), inits.get("roomInfo")) : null;
    }

}

