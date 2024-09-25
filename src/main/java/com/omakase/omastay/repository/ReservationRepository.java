package com.omakase.omastay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.Optional;

import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;


import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepositoryCustom {

   
    @Modifying
    @Query("UPDATE Reservation r SET r.resStatus = 3 WHERE r.startEndVo.end < CURRENT_TIMESTAMP AND r.resStatus = 1")
    void updateExpiredStatuses();

    // 하루가 지난 예약 중 Sale 테이블에 없는 예약의 ID를 조회
    @Query("SELECT r FROM Reservation r LEFT JOIN FETCH Sales s ON r.id = s.reservation.id WHERE r.startEndVo.end < :yesterday AND s.id IS NULL")
    List<Reservation> findExpiredReservationsNotInSale(@Param("yesterday") LocalDateTime yesterday);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reservation r WHERE r.roomInfo.id = :roomInfo AND r.startEndVo.start < :end AND r.startEndVo.end > :start")
    Optional<Reservation> findConflictingReservationWithLock(@Param("roomInfo") int roomInfo, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}