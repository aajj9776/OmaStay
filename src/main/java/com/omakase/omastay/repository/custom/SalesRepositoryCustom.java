package com.omakase.omastay.repository.custom;

import com.omakase.omastay.dto.custom.Top5SalesDTO;
import java.util.List;

public interface SalesRepositoryCustom {

    List<Top5SalesDTO> findTop5SalesByRegion(String region);

}
