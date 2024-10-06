package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Price;
import com.omakase.omastay.repository.custom.PriceRepositoryCustom;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Integer>, PriceRepositoryCustom {

    Price findFirstByHostInfoId(Integer id);

    List<Price> findAllByHostInfoId(Integer id);

    

}