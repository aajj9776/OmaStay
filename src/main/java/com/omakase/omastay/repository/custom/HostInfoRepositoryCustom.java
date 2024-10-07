package com.omakase.omastay.repository.custom;

import org.springframework.transaction.annotation.Transactional;

import com.omakase.omastay.dto.custom.AdminMainCustomDTO;

import java.util.List;

public interface HostInfoRepositoryCustom {
    List<Integer> keywordFiltering(String keyword);

    List<AdminMainCustomDTO> getRequestCount();
}
