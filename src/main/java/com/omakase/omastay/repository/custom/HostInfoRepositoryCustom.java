package com.omakase.omastay.repository.custom;


import com.omakase.omastay.dto.custom.AdminMainCustomDTO;
import com.omakase.omastay.dto.custom.FilterDTO;

import java.util.List;

public interface HostInfoRepositoryCustom {
    List<Integer> keywordFiltering(FilterDTO filterDTO);

    List<AdminMainCustomDTO> getRequestCount();
}
