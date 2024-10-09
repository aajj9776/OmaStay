package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Service;
import com.omakase.omastay.repository.custom.ServiceRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceRepository extends JpaRepository<Service, Integer>, ServiceRepositoryCustom {

    // 호스트 공지사항 게시글 삭제하기
    @Modifying
    @Transactional
    @Query("Update Service s set s.sStatus = 1 where s.id in :ids")
    int deleteById(@Param("ids") int[] ids);

    //파일과 함께 게시글 수정하기
    @Modifying
    @Query("UPDATE Service s SET s.sTitle = :#{#dto.sTitle}, s.sContent = :#{#dto.sContent}, s.fileName.fName = :#{#dto.fileName.fName}, s.fileName.oName = :#{#dto.fileName.oName} WHERE s.id = :#{#dto.id}")
    void modifyServices(@Param("dto") Service dto);

    //파일 없이 게시글 수정하기
    @Modifying
    @Query("UPDATE Service s SET s.sTitle = :#{#dto.sTitle}, s.sContent = :#{#dto.sContent} WHERE s.id = :#{#dto.id}")
    void modifyServicesNoFile(@Param("dto") Service dto);

    //파일을 삭제하여 게시글 수정하기
    @Modifying
    @Query("UPDATE Service s SET s.sTitle = :#{#dto.sTitle}, s.sContent = :#{#dto.sContent}, s.fileName.fName = null, s.fileName.oName = null WHERE s.id = :#{#dto.id}")
    void modifyServicesDeleteFile(@Param("dto") Service dto);

    
}