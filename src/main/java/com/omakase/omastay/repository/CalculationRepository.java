package com.omakase.omastay.repository;

import com.omakase.omastay.dto.custom.AdminMainCustomDTO;
import com.omakase.omastay.entity.Calculation;

import com.omakase.omastay.repository.custom.CalculationRepositoryCustom;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CalculationRepository extends JpaRepository<Calculation, Integer>, CalculationRepositoryCustom {

    //이번 달의 정산 리스트 가져오기
    @Query("SELECT c FROM Calculation c JOIN FETCH c.hostInfo WHERE c.calMonth = :thisMonth ")
    List<Calculation> calculationMonthly(@Param("thisMonth") LocalDateTime thisMonth);

    @Query("SELECT c FROM Calculation c JOIN FETCH c.hostInfo")
    List<Calculation> calculationAll();

    @Modifying
    @Query("UPDATE Calculation c SET c.calStatus = 1, c.calConfirmTime = CURRENT_TIMESTAMP WHERE c.id = :calIdx")
    void approveCalculation(@Param("calIdx") Integer calIdx);

    @Modifying
    @Query("UPDATE Calculation c SET c.calStatus = 2, c.calCompleteTime = CURRENT_TIMESTAMP WHERE c.id = :calIdx")
    void completeCalculation(@Param("calIdx") Integer calIdx);

    @Query("SELECT c FROM Calculation c JOIN FETCH c.hostInfo WHERE c.id = :id")
    Calculation findOneById(@Param("id") Integer id);

    
}