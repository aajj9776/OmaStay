package com.omakase.omastay.repository.custom;

import java.util.List;

public interface HostFacilitiesRepositoryCustom {
    List<Integer> findFacilitiesIdsByHostId(List<Integer> facilities, List<Integer> hostId);
}
