package com.omakase.omastay.repository;

import java.util.List;

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

}