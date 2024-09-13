package com.omakase.omastay.repository.custom;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.vo.StartEndVo;

import java.util.List;
import java.util.Set;

public interface RoomInfoRepositoryCustom {
    Set<Integer> dateFiltering(StartEndVo startEndDay, List<Integer> hostInfos);

    Set<HostInfo> personFiltering(int person, Set<Integer> hostInfos);
}
