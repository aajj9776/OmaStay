package com.omakase.omastay.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ImageRepositoryCustom {
    List<Tuple> findImageNamesByHostIds(List<Integer> hostIds);
}
