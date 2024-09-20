package com.omakase.omastay.repository.custom.impl;
import com.omakase.omastay.entity.QImage;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.repository.custom.ImageRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.omakase.omastay.entity.QImage.image;

public class ImageRepositoryImpl implements ImageRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public ImageRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> findImageNamesByHostIds(List<Integer> hostIds) {
        QImage imageSub = new QImage("imageSub");

        return queryFactory
                .select(image.imgName.fName, image.hostInfo.id)
                .from(image)
                .where(
                        image.hostInfo.id.in(hostIds)
                                .and(image.imgCate.eq(ImgCate.HOST))
                                .and(image.id.eq(
                                        JPAExpressions
                                                .select(imageSub.id.min())
                                                .from(imageSub)
                                                .where(imageSub.hostInfo.id.eq(image.hostInfo.id))
                                ))
                )
                .fetch();
    }
}
