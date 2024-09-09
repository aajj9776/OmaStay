package com.omakase.omastay.repository;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HostInfoRepository extends JpaRepository<HostInfo, Integer>, HostInfoRepositoryCustom {

    HostInfo findByAdminMemberId(int adIdx);

}