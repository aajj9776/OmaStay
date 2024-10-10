package com.omakase.omastay.repository.custom;

import java.util.List;
import com.omakase.omastay.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberReviewRepositoryCustom extends JpaRepository<Review, Integer> {

    // 리뷰 조회 쿼리
    @Query("SELECT r FROM Review r JOIN FETCH r.hostInfo h JOIN FETCH r.reservation res WHERE r.member.id = :memIdx AND r.revStatus = 0")
    List<Review> findReviewsWithRoomAndHotelByMemberId(@Param("memIdx") int memIdx);
}
