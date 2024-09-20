package com.omakase.omastay.repository.custom;

import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.Tuple;

import java.util.HashSet;
import java.util.List;

public interface RoomInfoRepositoryCustom {
    List<Tuple> dateFiltering(StartEndVo startEndDay, List<Integer> hostInfos);

    List<Tuple> personFiltering(int person, List<Integer> hostInfos); 


    HashSet<Tuple> findHostIdsByRoomIds(List<Integer> roomIds);
}
