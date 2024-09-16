package com.omakase.omastay.repository;

import com.omakase.omastay.entity.HostFacilities;
import com.omakase.omastay.repository.custom.HostFacilitiesRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HostFacilitiesRepository extends JpaRepository<HostFacilities, Integer>, HostFacilitiesRepositoryCustom {

    List<HostFacilities> findByHostInfoId(int hIdx);
}