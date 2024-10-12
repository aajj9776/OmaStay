package com.omakase.omastay.repository.custom.impl;

import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
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
        //리뷰 평점과 리뷰 총 갯수 구하기
        //리뷰 평점은 리뷰의 평점을 모두 더한 후 리뷰의 총 개수로 나눠야됨

        return queryFactory
                .select(
                        review.hostInfo.id, review.revRating.avg().coalesce(0.0), review.count()
                )
                .from(review)
                .where(review.hostInfo.id.in(hostIds)
                        .and(review.revStatus.eq(BooleanStatus.TRUE)))
                .groupBy(review.hostInfo.id)
                .fetch();
    }

    @Override
    public List<Review> searchHostReview(String type, String keyword, int hIdx) {
        return queryFactory.selectFrom(review)
                .where(
                    containsKeyword(type, keyword),
                    review.hostInfo.id.eq(hIdx),
                    review.revStatus.eq(BooleanStatus.TRUE)
                )
                .orderBy(review.id.desc())
                .fetch();
    }

    private BooleanExpression containsKeyword(String type, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        switch (type) {
            case "all":
                return review.revWriter.contains(keyword)
                        .or(review.revContent.contains(keyword));
            case "revWriter":
                return review.revWriter.contains(keyword);
            case "revContent":
                return review.revContent.contains(keyword);
            default:
                return null;
        }
    }

}
