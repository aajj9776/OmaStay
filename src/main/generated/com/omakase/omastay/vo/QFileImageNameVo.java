package com.omakase.omastay.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileImageNameVo is a Querydsl query type for FileImageNameVo
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFileImageNameVo extends BeanPath<FileImageNameVo> {

    private static final long serialVersionUID = -1755100176L;

    public static final QFileImageNameVo fileImageNameVo = new QFileImageNameVo("fileImageNameVo");

    public final StringPath fName = createString("fName");

    public final StringPath oName = createString("oName");

    public QFileImageNameVo(String variable) {
        super(FileImageNameVo.class, forVariable(variable));
    }

    public QFileImageNameVo(Path<? extends FileImageNameVo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileImageNameVo(PathMetadata metadata) {
        super(FileImageNameVo.class, metadata);
    }

}

