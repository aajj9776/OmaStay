package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Grade;
import com.omakase.omastay.repository.custom.GradeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer>, GradeRepositoryCustom {

}