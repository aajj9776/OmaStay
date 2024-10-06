package com.omakase.omastay.repository.custom;

import com.omakase.omastay.entity.Review;
import com.querydsl.core.Tuple;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Tuple> findReviewStatsByHostIds(List<Integer> hostIds);

    List<Review> searchHostReview(String type, String keyword, int hIdx);
}
