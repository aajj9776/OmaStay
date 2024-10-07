package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.repository.custom.FacilitiesRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacilitiesRepository extends JpaRepository<Facilities, Integer>, FacilitiesRepositoryCustom {

    List<Facilities> findAll();

    Facilities findById(int id);

    @Query ("SELECT f FROM Facilities f WHERE f.id = :id")
    Facilities findById2(@Param("id") Integer id);

    @Query("SELECT hf.facilities FROM HostFacilities hf WHERE hf.hostInfo.id = :hIdx")
    List<Facilities> findFacilitiesByHostId(@Param("hIdx") Integer hIdx);
}