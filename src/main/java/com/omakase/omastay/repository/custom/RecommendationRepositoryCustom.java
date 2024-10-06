package com.omakase.omastay.repository.custom;

import java.util.List;

import java.time.LocalDate;

import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.entity.enumurate.HCate;

public interface RecommendationRepositoryCustom {

    List<Recommendation> findR(HCate hCate, LocalDate startDate, LocalDate endDate);
}
