package com.omakase.omastay.repository;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.repository.custom.SalesRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Integer>, SalesRepositoryCustom {

    @Query("SELECT s FROM Sales s " +
           "JOIN FETCH s.reservation r " +
           "JOIN FETCH s.hostInfo h " +
           "JOIN FETCH r.payment p")
    List<Sales> getAllSales();


}