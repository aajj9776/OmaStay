package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReviewCommentDTO;
import com.omakase.omastay.entity.Review;
import com.omakase.omastay.entity.ReviewComment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:37+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class ReviewCommentMapperImpl implements ReviewCommentMapper {

    @Override
    public ReviewCommentDTO toReviewCommentDTO(ReviewComment reviewComment) {
        if ( reviewComment == null ) {
            return null;
        }

        ReviewCommentDTO reviewCommentDTO = new ReviewCommentDTO();

        reviewCommentDTO.setRevIdx( reviewCommentReviewId( reviewComment ) );
        reviewCommentDTO.setId( reviewComment.getId() );
        reviewCommentDTO.setRcComment( reviewComment.getRcComment() );
        reviewCommentDTO.setRcDate( reviewComment.getRcDate() );
        reviewCommentDTO.setRcStatus( reviewComment.getRcStatus() );
        reviewCommentDTO.setRcNone( reviewComment.getRcNone() );

        return reviewCommentDTO;
    }

    @Override
    public ReviewComment toReviewComment(ReviewCommentDTO reviewCommentDTO) {
        if ( reviewCommentDTO == null ) {
            return null;
        }

        ReviewComment reviewComment = new ReviewComment();

        reviewComment.setReview( reviewCommentDTOToReview( reviewCommentDTO ) );
        reviewComment.setId( reviewCommentDTO.getId() );
        reviewComment.setRcComment( reviewCommentDTO.getRcComment() );
        reviewComment.setRcDate( reviewCommentDTO.getRcDate() );
        reviewComment.setRcStatus( reviewCommentDTO.getRcStatus() );
        reviewComment.setRcNone( reviewCommentDTO.getRcNone() );

        return reviewComment;
    }

    @Override
    public List<ReviewCommentDTO> toReviewCommentDTOList(List<ReviewComment> reviewCommentList) {
        if ( reviewCommentList == null ) {
            return null;
        }

        List<ReviewCommentDTO> list = new ArrayList<ReviewCommentDTO>( reviewCommentList.size() );
        for ( ReviewComment reviewComment : reviewCommentList ) {
            list.add( toReviewCommentDTO( reviewComment ) );
        }

        return list;
    }

    @Override
    public List<ReviewComment> toReviewCommentList(List<ReviewCommentDTO> reviewCommentDTOList) {
        if ( reviewCommentDTOList == null ) {
            return null;
        }

        List<ReviewComment> list = new ArrayList<ReviewComment>( reviewCommentDTOList.size() );
        for ( ReviewCommentDTO reviewCommentDTO : reviewCommentDTOList ) {
            list.add( toReviewComment( reviewCommentDTO ) );
        }

        return list;
    }

    private Integer reviewCommentReviewId(ReviewComment reviewComment) {
        if ( reviewComment == null ) {
            return null;
        }
        Review review = reviewComment.getReview();
        if ( review == null ) {
            return null;
        }
        Integer id = review.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Review reviewCommentDTOToReview(ReviewCommentDTO reviewCommentDTO) {
        if ( reviewCommentDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setId( reviewCommentDTO.getRevIdx() );

        return review;
    }
}
