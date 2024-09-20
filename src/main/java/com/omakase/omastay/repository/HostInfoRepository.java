package com.omakase.omastay.repository;

import com.omakase.omastay.dto.custom.HostInfoCustomDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HostInfoRepository extends JpaRepository<HostInfo, Integer>, HostInfoRepositoryCustom {

    HostInfo findByAdminMemberId(int adIdx);
    HostInfo findById(int id);
    
    @Query("SELECT h FROM HostInfo h ORDER BY h.id DESC")
    List<HostInfo> hostInfos();

}