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
}