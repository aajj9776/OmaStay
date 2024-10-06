package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer>, SalesRepositoryCustom {

    @Query("SELECT s FROM Sales s " +
           "JOIN FETCH s.reservation r " +
           "JOIN FETCH s.hostInfo h " +
           "JOIN FETCH r.payment p")
    List<Sales> getAllSales();


    @Query("SELECT s FROM Sales s " +
        "JOIN FETCH s.reservation r " +
        "JOIN FETCH s.hostInfo h " +
        "JOIN FETCH r.payment p " +
        "WHERE s.hostInfo.id = :hidx " +
        "AND s.salDate BETWEEN :startDate AND :endDate")
    List<Sales> findByHidx(@Param("startDate") LocalDate startDate, 
                        @Param("endDate") LocalDate endDate, 
                        @Param("hidx") Integer hidx);

}