package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

import static com.omakase.omastay.entity.QReview.review;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Tuple> findReviewStatsByHostIds(List<Integer> hostIds) {
        //리뷰 평점과 리뷰 몇명이 남겼는지 가져오기
        //리뷰 평점은 리뷰의 평점을 모두 더한 후 리뷰의 개수로 나눠야됨

        return queryFactory
                .select(
                        review.hostInfo.id, review.revRating.avg(), review.count()
                )
                .from(review)
                .where(review.hostInfo.id.in(hostIds))
                .groupBy(review.hostInfo.id)
                .fetch();
    }
}
