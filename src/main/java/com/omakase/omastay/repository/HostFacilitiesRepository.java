package com.omakase.omastay.repository;

import com.omakase.omastay.dto.HostFacilitiesDTO;
import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.entity.HostFacilities;
import com.omakase.omastay.repository.custom.HostFacilitiesRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HostFacilitiesRepository extends JpaRepository<HostFacilities, Integer>, HostFacilitiesRepositoryCustom {
    List<HostFacilities> findByHostInfoId(int hIdx);

    @Query("SELECT new com.omakase.omastay.dto.HostFacilitiesDTO(hf.id, hf.facilities.id, hf.hostInfo.id, null) FROM HostFacilities hf WHERE hf.hostInfo.id = :id")
    List<HostFacilitiesDTO> findByHostInfoIdAndFacilities(@Param("id") int id);

   
}