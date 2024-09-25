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

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepositoryCustom {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reservation r WHERE r.roomInfo.id = :roomInfo AND r.startEndVo.start < :end AND r.startEndVo.end > :start")
    Optional<Reservation> findConflictingReservationWithLock(@Param("roomInfo") int roomInfo, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


}