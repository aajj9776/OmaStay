package com.omakase.omastay.service;

import com.omakase.omastay.repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;


}
