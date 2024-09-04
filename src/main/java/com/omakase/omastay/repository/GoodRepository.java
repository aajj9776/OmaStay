package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Good;
import com.omakase.omastay.repository.custom.GoodRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Integer>, GoodRepositoryCustom {

}