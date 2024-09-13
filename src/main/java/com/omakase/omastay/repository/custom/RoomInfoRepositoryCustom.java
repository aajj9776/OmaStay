package com.omakase.omastay.repository.custom;

import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.Tuple;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface RoomInfoRepositoryCustom {
    Set<Integer> dateFiltering(StartEndVo startEndDay, List<Integer> hostInfos);

    List<Integer> personFiltering(int person, Set<Integer> hostInfos);

    HashSet<Tuple> findHostIdsByRoomIds(List<Integer> roomIdxs);
}
