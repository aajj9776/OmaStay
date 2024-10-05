package com.omakase.omastay.repository.custom;

import java.util.List;

import com.omakase.omastay.dto.CalculationDTO;
import com.omakase.omastay.dto.custom.HostCalculationDTO;

public interface CalculationRepositoryCustom {

    CalculationDTO findHostAndMonth(Integer hidx, Integer calMonth);
}
