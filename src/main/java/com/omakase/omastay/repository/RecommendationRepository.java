package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer>, RecommendationRepositoryCustom {

}