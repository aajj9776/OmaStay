package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Service;
import com.omakase.omastay.repository.custom.ServiceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;


public interface ServiceRepository extends JpaRepository<Service, Integer>, ServiceRepositoryCustom {

    // 호스트 공지사항 전체 가져오기
    @Query("SELECT s FROM Service s WHERE s.sCate = :sCate AND s.sAuth = :sAuth")
    List<Service> findBySCateAndSAuth(@Param("sCate") SCate sCate, @Param("sAuth") UserAuth sAuth);

    //List<Service> findBySCateAndSAuth(@Param("sCate") SCate sCate, @Param("sAuth") UserAuth sAuth);

    @Query("Update Service s set s.sStatus = 1 where s.id in :ids")
    int deleteById(@Param("ids") int[] ids);
}