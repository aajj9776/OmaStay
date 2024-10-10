package com.omakase.omastay.repository;

import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.custom.RoomInfoRepositoryCustom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.threeten.bp.LocalDateTime;

import java.time.LocalDate;

import java.util.List;

public interface RoomInfoRepository extends JpaRepository<RoomInfo, Integer>, RoomInfoRepositoryCustom {

    RoomInfo findByHostInfoId(int hIdx);

    RoomInfo findById(int id);

    List<RoomInfo> findByHostInfoAndRoomStatus(HostInfo hostInfo, BooleanStatus roomStatus);

    @Modifying
    @Transactional
    @Query("Update RoomInfo r set r.roomStatus = 1 where r.id in :ids")
    int deleteById(@Param("ids") int[] ids);
    List<RoomInfo> findByHostInfo(HostInfo hostInfo);

    @Query("SELECT r FROM RoomInfo r " +
            "LEFT JOIN Reservation res ON r.id = res.roomInfo.id " +
            "AND res.resStatus NOT IN (2, 3) " +
            "AND (" +
            "    (FUNCTION('DATE', res.startEndVo.start) <= :checkout AND FUNCTION('DATE', res.startEndVo.end) >= :checkin) " +
            "    OR " +
            "    (FUNCTION('DATE', res.startEndVo.start) <= :checkin AND FUNCTION('DATE', res.startEndVo.end) >= :checkin) " +
            "    OR " +
            "    (FUNCTION('DATE', res.startEndVo.start) <= :checkout AND FUNCTION('DATE', res.startEndVo.end) >= :checkout)" +
            ") " +
            "WHERE r.hostInfo.id = :hIdx " +
            "AND r.roomPerson >= :person " +
            "AND res.id IS NULL")
        List<RoomInfo> findAvailableRooms(@Param("hIdx") Integer hIdx,
                                    @Param("checkin") LocalDate checkin,
                                    @Param("checkout") LocalDate checkout,
                                    @Param("person") Integer person);


    @Query("SELECT r FROM RoomInfo r WHERE r.hostInfo.id = :hIdx")
    List<RoomInfo> findAllRommHidx(@Param("hIdx") Integer hIdx);




}