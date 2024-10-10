package com.omakase.omastay.repository.custom;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.Sales;

import java.time.LocalDate;
import java.util.List;

import com.omakase.omastay.dto.custom.CalculationCustomDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;

public interface SalesRepositoryCustom {

    //관리자 판매 실적 - 이번달 전체 지역 판매 실적 Top5
    List<Top5SalesDTO> getTop5SalesThisMonth(LocalDate firstDay, LocalDate today);

    //관리자 판매 실적 - 판매 실적 테이블 검색
    List<Sales> searchSales(String startDate, String endDate, String region);

    //관리자 판매 실적 - 판매 실적 Top5 검색
    List<Top5SalesDTO> searchTop5Sales(String startDate, String endDate, String region);
   
    List<HostSalesDTO> findHostSales(Integer hidx);

    List<HostSalesDTO> searchHostSales(String roomType, String startDate, String endDate, Integer hidx);

    List<HostSalesDTO> findHostYearSales(Integer hidx, Integer year);

    List<HostSalesDTO> findHostMonthSales(Integer hidx, Integer year, Integer month);

    CalculationCustomDTO findHostMonthPayment(Integer hIdx, LocalDate firstDay, LocalDate lastDay);

    
}
