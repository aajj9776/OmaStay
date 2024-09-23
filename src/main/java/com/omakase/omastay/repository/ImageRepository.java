package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Image;
import com.omakase.omastay.repository.custom.ImageRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<Image, Integer>, ImageRepositoryCustom {

    List<Image> findByHostInfoId(int hIdx);

    @Query("SELECT i FROM Image i WHERE i.roomInfo.id = :rIdx")
    List<Image> findByRoomInfoId(@Param("rIdx") Integer rIdx);
}