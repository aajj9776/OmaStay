package com.omakase.omastay.repository.custom;

import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.entity.enumurate.HCate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HostInfoRepositoryCustom {
    List<Integer> keywordFiltering(FilterDTO filterDTO);
}
