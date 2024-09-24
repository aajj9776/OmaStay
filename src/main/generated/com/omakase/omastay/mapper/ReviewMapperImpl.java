package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Review;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:36+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDTO toReviewDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        Integer id = reviewMemberId( review );
        if ( id != null ) {
            reviewDTO.setMemIdx( id );
        }
        Integer id1 = reviewReservationId( review );
        if ( id1 != null ) {
            reviewDTO.setResIdx( id1 );
        }
        Integer id2 = reviewHostInfoId( review );
        if ( id2 != null ) {
            reviewDTO.setHIdx( id2 );
        }
        reviewDTO.setRevFileImageNameVo( review.getRevFileImageNameVo() );
        if ( review.getId() != null ) {
            reviewDTO.setId( review.getId() );
        }
        reviewDTO.setRevWriter( review.getRevWriter() );
        reviewDTO.setRevContent( review.getRevContent() );
        reviewDTO.setRevDate( review.getRevDate() );
        reviewDTO.setRevStatus( review.getRevStatus() );
        reviewDTO.setRevRating( review.getRevRating() );
        reviewDTO.setRevNone( review.getRevNone() );

        return reviewDTO;
    }

    @Override
    public Review toReview(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setMember( reviewDTOToMember( reviewDTO ) );
        review.setReservation( reviewDTOToReservation( reviewDTO ) );
        review.setHostInfo( reviewDTOToHostInfo( reviewDTO ) );
        review.setId( reviewDTO.getId() );
        review.setRevWriter( reviewDTO.getRevWriter() );
        review.setRevContent( reviewDTO.getRevContent() );
        review.setRevDate( reviewDTO.getRevDate() );
        review.setRevStatus( reviewDTO.getRevStatus() );
        review.setRevNone( reviewDTO.getRevNone() );
        review.setRevFileImageNameVo( reviewDTO.getRevFileImageNameVo() );
        review.setRevRating( reviewDTO.getRevRating() );

        return review;
    }

    @Override
    public List<ReviewDTO> toReviewDTOList(List<Review> reviewList) {
        if ( reviewList == null ) {
            return null;
        }

        List<ReviewDTO> list = new ArrayList<ReviewDTO>( reviewList.size() );
        for ( Review review : reviewList ) {
            list.add( toReviewDTO( review ) );
        }

        return list;
    }

    @Override
    public List<Review> toReviewList(List<ReviewDTO> reviewDTOList) {
        if ( reviewDTOList == null ) {
            return null;
        }

        List<Review> list = new ArrayList<Review>( reviewDTOList.size() );
        for ( ReviewDTO reviewDTO : reviewDTOList ) {
            list.add( toReview( reviewDTO ) );
        }

        return list;
    }

    private Integer reviewMemberId(Review review) {
        if ( review == null ) {
            return null;
        }
        Member member = review.getMember();
        if ( member == null ) {
            return null;
        }
        Integer id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer reviewReservationId(Review review) {
        if ( review == null ) {
            return null;
        }
        Reservation reservation = review.getReservation();
        if ( reservation == null ) {
            return null;
        }
        Integer id = reservation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer reviewHostInfoId(Review review) {
        if ( review == null ) {
            return null;
        }
        HostInfo hostInfo = review.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Member reviewDTOToMember(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( reviewDTO.getMemIdx() );

        return member;
    }

    protected Reservation reviewDTOToReservation(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setId( reviewDTO.getResIdx() );

        return reservation;
    }

    protected HostInfo reviewDTOToHostInfo(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( reviewDTO.getHIdx() );

        return hostInfo;
    }
}
