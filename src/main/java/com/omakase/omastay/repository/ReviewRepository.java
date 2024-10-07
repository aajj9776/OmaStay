package com.omakase.omastay.repository;
 
import com.omakase.omastay.dto.custom.ReviewMemberDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

    List<Review> findByHostInfoAndRevStatus(HostInfo hostInfo, BooleanStatus revStatus);
    List<Review> findByMemberId(int memIdx);

    @Query("SELECT r FROM Review r JOIN FETCH r.hostInfo h JOIN FETCH r.reservation res WHERE r.member.id = :memIdx AND r.revStatus = 0")
    List<Review> findReviewsWithRoomAndHotelByMemberId(@Param("memIdx") int memIdx);
}