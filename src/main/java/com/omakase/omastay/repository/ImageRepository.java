package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Image;
import com.omakase.omastay.repository.custom.ImageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer>, ImageRepositoryCustom {
}