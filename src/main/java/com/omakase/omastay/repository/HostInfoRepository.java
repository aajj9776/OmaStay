package com.omakase.omastay.repository;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.repository.custom.HostInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HostInfoRepository extends JpaRepository<HostInfo, Integer>, HostInfoRepositoryCustom {

    HostInfo findByAdminMemberId(int adIdx);
    HostInfo findById(int id);
    
    @Query("SELECT h FROM HostInfo h WHERE h.hStatus IS NOT NULL ORDER BY h.id DESC")
    List<HostInfo> hostInfos();

    @Modifying 
    @Query("UPDATE HostInfo h SET h.hStatus = 1 WHERE h.id = :hidx")
    void approveHost(@Param("hidx") int hidx);
    
    @Modifying 
    @Query("UPDATE HostInfo h SET h.hStatus = 2 WHERE h.id = :hidx")
    void rejectHost(@Param("hidx") int hidx);
}