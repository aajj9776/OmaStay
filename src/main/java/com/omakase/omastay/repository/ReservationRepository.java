package com.omakase.omastay.repository;

import java.util.List;
import java.time.LocalDateTime;

import com.omakase.omastay.dto.custom.HostReservationDTO;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;

import org.apache.tomcat.util.http.parser.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepositoryCustom {

    List<Reservation> findByRoomInfo(RoomInfo roomInfo);

    @Modifying
    @Transactional
    @Query("Update Reservation r set r.resStatus = 1 where r.id in :ids AND r.resStatus = 0")
    int confirmById(@Param("ids") int[] ids);

    @Modifying
    @Transactional
    @Query("Update Reservation r set r.resStatus = 2 where r.id in :ids AND (r.resStatus = 0 or r.resStatus = 1)")
    int rejectById(@Param("ids") int[] ids);

    @Modifying
    @Query("UPDATE Reservation r SET r.resStatus = 3 WHERE r.startEndVo.end < CURRENT_TIMESTAMP AND r.resStatus = 1")
    void updateExpiredStatuses();

    // 하루가 지난 예약 중 Sale 테이블에 없는 예약의 ID를 조회
    @Query("SELECT r FROM Reservation r LEFT JOIN FETCH Sales s ON r.id = s.reservation.id WHERE r.startEndVo.end < :yesterday AND s.id IS NULL")
    List<Reservation> findExpiredReservationsNotInSale(@Param("yesterday") LocalDateTime yesterday);
}