package com.omakase.omastay.repository;

import com.omakase.omastay.entity.RoomFacilities;
import com.omakase.omastay.repository.custom.RoomFacilitiesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomFacilitiesRepository extends JpaRepository<RoomFacilities, Integer>, RoomFacilitiesRepositoryCustom {

}