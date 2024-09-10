package com.omakase.omastay.repository.custom;

import com.omakase.omastay.entity.HostInfo;

import java.util.List;

public interface HostInfoRepositoryCustom {

    List<HostInfo> keywordFiltering(String keyword);

}
