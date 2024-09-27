package com.omakase.omastay.repository.custom;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HostInfoRepositoryCustom {
    List<Integer> keywordFiltering(String keyword);
}
