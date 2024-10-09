package com.omakase.omastay.repository.custom;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.Sales;

import java.time.LocalDate;
import java.util.List;

import com.omakase.omastay.dto.custom.CalculationCustomDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;

public interface SalesRepositoryCustom {

    List<Top5SalesDTO> findTop5SalesByRegion(String region);

    List<Sales> searchSales(String startDate, String endDate, String region);
   
    List<HostSalesDTO> findHostSales(Integer hidx);

    List<HostSalesDTO> searchHostSales(String roomType, String startDate, String endDate, Integer hidx);

    List<HostSalesDTO> findHostYearSales(Integer hidx, Integer year);

    List<HostSalesDTO> findHostMonthSales(Integer hidx, Integer year, Integer month);

    CalculationCustomDTO findHostMonthPayment(Integer hIdx, LocalDate firstDay, LocalDate lastDay);

    
}
