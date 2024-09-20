package com.omakase.omastay.repository;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.List;


public interface RoomInfoRepository extends JpaRepository<RoomInfo, Integer>, RoomInfoRepositoryCustom {

    RoomInfo findByHostInfoId(int hIdx);

    RoomInfo findById(int id);

    List<RoomInfo> findByHostInfo(HostInfo hostInfo);

}