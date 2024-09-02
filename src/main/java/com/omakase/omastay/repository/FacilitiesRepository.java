package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Facilities;
import com.omakase.omastay.repository.custom.FacilitiesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilitiesRepository extends JpaRepository<Facilities, Integer>, FacilitiesRepositoryCustom {

}