package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Point;
import com.omakase.omastay.repository.custom.PointRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer>, PointRepositoryCustom {

}