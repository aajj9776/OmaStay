package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Inquiry;
import com.omakase.omastay.repository.custom.InquiryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>, InquiryRepositoryCustom {

    // @Query("SELECT i FROM Inquiry i WHERE i.iqStatus = 'TRUE' ORDER BY i.id DESC")
    // List<Inquiry> getAllInquiries();

    @Query("SELECT i FROM Inquiry i ORDER BY i.id DESC")
    List<Inquiry> getAllInquiries();
}