package com.omakase.omastay.repository.custom;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.entity.Sales;

import java.util.List;

public interface SalesRepositoryCustom {

    List<Top5SalesDTO> findTop5SalesByRegion(String region);

    List<Sales> searchSales(String startDate, String endDate, String region);
}
