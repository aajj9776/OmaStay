package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Integer>, SalesRepositoryCustom {

}