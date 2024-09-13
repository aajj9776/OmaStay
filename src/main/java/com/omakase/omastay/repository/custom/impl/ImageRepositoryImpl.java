package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.repository.custom.ImageRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.omakase.omastay.entity.QImage.image;

public class ImageRepositoryImpl implements ImageRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ImageRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<String> findImageNamesByHostIds(List<Integer> hostIds) {
        return queryFactory
                .select(image.imgName.fName)
                .from(image)
                .where(image.hostInfo.id.in(hostIds)
                        .and(image.imgCate.eq(ImgCate.HOST)))
                .fetch();
    }
}
