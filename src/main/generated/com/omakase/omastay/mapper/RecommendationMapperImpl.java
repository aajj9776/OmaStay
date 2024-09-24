package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Recommendation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:37+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class RecommendationMapperImpl implements RecommendationMapper {

    @Override
    public RecommendationDTO toRecommendationDTO(Recommendation recommendation) {
        if ( recommendation == null ) {
            return null;
        }

        RecommendationDTO recommendationDTO = new RecommendationDTO();

        recommendationDTO.setHIdx( recommendationHostInfoId( recommendation ) );
        recommendationDTO.setId( recommendation.getId() );
        recommendationDTO.setRecPoint( recommendation.getRecPoint() );
        recommendationDTO.setRecNone( recommendation.getRecNone() );

        return recommendationDTO;
    }

    @Override
    public Recommendation toRecommendation(RecommendationDTO recommendationDTO) {
        if ( recommendationDTO == null ) {
            return null;
        }

        Recommendation recommendation = new Recommendation();

        recommendation.setHostInfo( recommendationDTOToHostInfo( recommendationDTO ) );
        recommendation.setId( recommendationDTO.getId() );
        recommendation.setRecPoint( recommendationDTO.getRecPoint() );
        recommendation.setRecNone( recommendationDTO.getRecNone() );

        return recommendation;
    }

    @Override
    public List<RecommendationDTO> toRecommendationDTOList(List<Recommendation> recommendationList) {
        if ( recommendationList == null ) {
            return null;
        }

        List<RecommendationDTO> list = new ArrayList<RecommendationDTO>( recommendationList.size() );
        for ( Recommendation recommendation : recommendationList ) {
            list.add( toRecommendationDTO( recommendation ) );
        }

        return list;
    }

    @Override
    public List<Recommendation> toRecommendationList(List<RecommendationDTO> recommendationDTOList) {
        if ( recommendationDTOList == null ) {
            return null;
        }

        List<Recommendation> list = new ArrayList<Recommendation>( recommendationDTOList.size() );
        for ( RecommendationDTO recommendationDTO : recommendationDTOList ) {
            list.add( toRecommendation( recommendationDTO ) );
        }

        return list;
    }

    private Integer recommendationHostInfoId(Recommendation recommendation) {
        if ( recommendation == null ) {
            return null;
        }
        HostInfo hostInfo = recommendation.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected HostInfo recommendationDTOToHostInfo(RecommendationDTO recommendationDTO) {
        if ( recommendationDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( recommendationDTO.getHIdx() );

        return hostInfo;
    }
}
