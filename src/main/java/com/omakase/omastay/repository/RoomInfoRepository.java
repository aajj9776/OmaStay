package com.omakase.omastay.repository;

import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Integer>, RoomInfoRepositoryCustom {

}