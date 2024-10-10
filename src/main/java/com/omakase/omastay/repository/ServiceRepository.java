package com.omakase.omastay.repository;

import java.util.Optional;
import com.omakase.omastay.entity.Service;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.repository.custom.ServiceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceRepository extends JpaRepository<Service, Integer>, ServiceRepositoryCustom {

    @Modifying
    @Transactional
    @Query("Update Service s set s.sStatus = 1 where s.id in :ids")
    int deleteById(@Param("ids") int[] ids);

    @Query("SELECT s FROM Service s WHERE s.id = :id AND s.sCate = :sCate")
    Optional<Service> findByIdAndSCate(@Param("id") int id, @Param("sCate") SCate sCate);
}