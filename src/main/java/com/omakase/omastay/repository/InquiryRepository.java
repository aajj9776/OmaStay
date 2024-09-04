package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Inquiry;
import com.omakase.omastay.repository.custom.InquiryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>, InquiryRepositoryCustom {

}