package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Visitor;
import com.omakase.omastay.repository.custom.VisitorRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepository extends JpaRepository<Visitor, Integer>, VisitorRepositoryCustom {

}