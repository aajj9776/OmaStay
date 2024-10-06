package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.omakase.omastay.entity.enumurate.HCate;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer>, RecommendationRepositoryCustom {


    //시간을 내림차순으로 정렬해서 가장 가까운 날짜의 localdate를 가져와서 해당하는 추천 목록을 hcate와 같은 것만 가져옴
    @Query("SELECT r " +
              "FROM Recommendation r " +
              "JOIN FETCH r.hostInfo " +
              "WHERE r.recType = :hCate " +
              "AND FUNCTION('DATE', r.recDate) = (" +
              "   SELECT FUNCTION('DATE', MAX(r2.recDate)) " +
              "   FROM Recommendation r2" +
              ") " +
              "ORDER BY r.recDate DESC")
    List<Recommendation> findByHCate(@Param("hCate") HCate hCate);
}