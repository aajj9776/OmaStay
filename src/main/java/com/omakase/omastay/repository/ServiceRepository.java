package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Service;
import com.omakase.omastay.repository.custom.ServiceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer>, ServiceRepositoryCustom {

}