package com.omakase.omastay.repository.custom;

import com.omakase.omastay.entity.Price;

import java.util.List;

public interface PriceRepositoryCustom {
    List<Price> findAvgPriceByHostIds(List<Integer> hostIds);
}
