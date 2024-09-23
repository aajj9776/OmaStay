package com.omakase.omastay.repository;
 
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

    List<Review> findByHostInfoAndRevStatus(HostInfo hostInfo, BooleanStatus revStatus);

}