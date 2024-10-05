package com.omakase.omastay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import jakarta.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.Optional;

import java.util.List;

import com.omakase.omastay.dto.custom.MemberCustomDTO;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;
import org.springframework.data.jpa.repository.Modifying;
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

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reservation r WHERE r.roomInfo.id = :roomInfo AND r.startEndVo.start < :end AND r.startEndVo.end > :start")
    Optional<Reservation> findConflictingReservationWithLock(@Param("roomInfo") int roomInfo, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);


    //admin의 회원조회에서 회원의 최근 3개월 예약 횟수를 가져옴
    @Query("SELECT COUNT(r) FROM Reservation r JOIN r.payment p WHERE r.member.id = :memId AND p.payDate >= :time")
    Integer get3MonthCount(@Param("memId") Integer memId, @Param("time") LocalDateTime time);
    
    //admin의 회원조회에서 회원의 전체 예약 횟수를 가져옴
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.member.id = :memId")
    Integer getTotalCount(@Param("memId") Integer memId);

}