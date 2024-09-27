package com.omakase.omastay.repository.custom;

import java.util.List;

import com.omakase.omastay.dto.custom.HostSalesDTO;


public interface SalesRepositoryCustom {
   
    List<HostSalesDTO> findHostSales(Integer hidx);

    List<HostSalesDTO> searchHostSales(String roomType, String startDate, String endDate, Integer hidx);

    List<HostSalesDTO> findHostYearSales(Integer hidx, Integer year);

    List<HostSalesDTO> findHostMonthSales(Integer hidx, Integer year, Integer month);
    
}
