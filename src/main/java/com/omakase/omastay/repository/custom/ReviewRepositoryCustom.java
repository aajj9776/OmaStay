package com.omakase.omastay.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Tuple> findReviewStatsByHostIds(List<Integer> hostIds);
}
