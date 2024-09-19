package com.omakase.omastay.repository;
 
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.repository.custom.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer>, ReviewRepositoryCustom {

}