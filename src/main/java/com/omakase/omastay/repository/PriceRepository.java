package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Price;
import com.omakase.omastay.repository.custom.PriceRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
public interface PriceRepository extends JpaRepository<Price, Integer>, PriceRepositoryCustom {

    
    Price findFirstByRoomInfoId(Integer id);

    Price findFirstByHostInfoId(Integer id);

    List<Price> findAllByHostInfoId(Integer id);

    List<Price> findAll();

    Price findByRoomInfoId(Integer Id);
    
    @Query("SELECT p FROM Price p WHERE p.hostInfo.id = :hIdx AND p.roomInfo.id = :roomIdx " +
    "AND ((:checkOut >= FUNCTION('DATE', p.peakVo.peakStart) AND :checkIn <= FUNCTION('DATE', p.peakVo.peakEnd)) OR " +
    "(:checkOut >= FUNCTION('DATE', p.semi.semiStart) AND :checkIn <= FUNCTION('DATE', p.semi.semiEnd)) OR " +
    "(p.regularPrice IS NOT NULL))")
    List<Price> findPricesForRoom(@Param("hIdx") Integer hIdx,
                              @Param("roomIdx") Integer roomIdx,
                              @Param("checkIn") LocalDate checkIn,
                              @Param("checkOut") LocalDate checkOut);
}

    
