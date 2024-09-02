package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Calculation;

import com.omakase.omastay.repository.custom.CalculationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Integer>, CalculationRepositoryCustom {

}