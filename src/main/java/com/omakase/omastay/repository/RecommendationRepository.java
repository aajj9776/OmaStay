package com.omakase.omastay.repository;

import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;

import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RecommendationRepository extends JpaRepository<Recommendation, Integer>, RecommendationRepositoryCustom {
    @Query("SELECT rec FROM Recommendation rec JOIN FETCH rec.hostInfo h " +
       "ORDER BY " +
       "CASE " +
       "    WHEN h.hCate = 0 THEN rec.recPoint " +
       "    WHEN h.hCate = 1 THEN rec.recPoint " +
       "    WHEN h.hCate = 2 THEN rec.recPoint " +
       "    WHEN h.hCate = 3 THEN rec.recPoint " +
       "END DESC")
    List<Recommendation> findAllWithSort();

}                                                 