package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "member.id", target = "memIdx")
    @Mapping(source = "reservation.id", target = "resIdx")
    @Mapping(source = "hostInfo.id", target = "HIdx")
    @Mapping(source = "revFileImageNameVo" , target = "revFileImageNameVo")
    ReviewDTO toReviewDTO(Review review);

    @Mapping(source = "memIdx", target = "member.id")
    @Mapping(source = "resIdx", target = "reservation.id")
    @Mapping(source = "HIdx", target = "hostInfo.id")
    Review toReview(ReviewDTO reviewDTO);

    List<ReviewDTO> toReviewDTOList(List<Review> reviewList);

    List<Review> toReviewList(List<ReviewDTO> reviewDTOList);
}