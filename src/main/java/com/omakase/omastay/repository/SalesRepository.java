package com.omakase.omastay.repository;

import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalesRepository extends JpaRepository<Sales, Integer>, SalesRepositoryCustom {


}