package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.entity.Recommendation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RecommendationMapper {
    RecommendationMapper INSTANCE = Mappers.getMapper(RecommendationMapper.class);

    @Mapping(source = "hostInfo.id", target = "HIdx")
    RecommendationDTO toRecommendationDTO(Recommendation recommendation);

    @Mapping(source = "HIdx", target = "hostInfo.id")
    Recommendation toRecommendation(RecommendationDTO recommendationDTO);

    List<RecommendationDTO> toRecommendationDTOList(List<Recommendation> recommendationList);

    List<Recommendation> toRecommendationList(List<RecommendationDTO> recommendationDTOList);
}