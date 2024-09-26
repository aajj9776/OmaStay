package com.omakase.omastay.repository;
import java.util.List;

import com.omakase.omastay.entity.ReviewComment;
import com.omakase.omastay.repository.custom.RecommendationRepositoryCustom;

import io.lettuce.core.dynamic.annotation.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer>, RecommendationRepositoryCustom {  
}