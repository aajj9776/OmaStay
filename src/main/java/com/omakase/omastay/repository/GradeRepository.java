package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Grade;
import com.omakase.omastay.repository.custom.GradeRepositoryCustom;

import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GradeRepository extends JpaRepository<Grade, Integer>, GradeRepositoryCustom {
}      