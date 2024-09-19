package com.omakase.omastay.repository.custom;

import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.Tuple;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface PriceRepositoryCustom {


    List<Tuple> findAvgPriceByHostIds(List<Integer> hostIds, @NotNull StartEndVo startEndDay);
}
