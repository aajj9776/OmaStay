package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReviewCommentDTO;
import com.omakase.omastay.entity.ReviewComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewCommentMapper {
    ReviewCommentMapper INSTANCE = Mappers.getMapper(ReviewCommentMapper.class);

    @Mapping(source = "review.id", target = "revIdx")
    ReviewCommentDTO toReviewCommentDTO(ReviewComment reviewComment);

    @Mapping(source = "revIdx", target = "review.id")
    ReviewComment toReviewComment(ReviewCommentDTO reviewCommentDTO);

    List<ReviewCommentDTO> toReviewCommentDTOList(List<ReviewComment> reviewCommentList);

    List<ReviewComment> toReviewCommentList(List<ReviewCommentDTO> reviewCommentDTOList);
}