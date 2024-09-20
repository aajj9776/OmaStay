package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Point;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class PointMapperImpl implements PointMapper {

    @Override
    public PointDTO toPointDTO(Point point) {
        if ( point == null ) {
            return null;
        }

        PointDTO pointDTO = new PointDTO();

        pointDTO.setMemIdx( pointMemberId( point ) );
        pointDTO.setId( point.getId() );
        pointDTO.setPSum( point.getPSum() );
        pointDTO.setPValue( point.getPValue() );
        pointDTO.setPDate( point.getPDate() );

        return pointDTO;
    }

    @Override
    public Point toPoint(PointDTO pointDTO) {
        if ( pointDTO == null ) {
            return null;
        }

        Point point = new Point();

        point.setMember( pointDTOToMember( pointDTO ) );
        point.setId( pointDTO.getId() );
        point.setPSum( pointDTO.getPSum() );
        point.setPValue( pointDTO.getPValue() );
        point.setPDate( pointDTO.getPDate() );

        return point;
    }

    @Override
    public List<PointDTO> toPointDTOList(List<Point> pointList) {
        if ( pointList == null ) {
            return null;
        }

        List<PointDTO> list = new ArrayList<PointDTO>( pointList.size() );
        for ( Point point : pointList ) {
            list.add( toPointDTO( point ) );
        }

        return list;
    }

    @Override
    public List<Point> toPointList(List<PointDTO> pointDTOList) {
        if ( pointDTOList == null ) {
            return null;
        }

        List<Point> list = new ArrayList<Point>( pointDTOList.size() );
        for ( PointDTO pointDTO : pointDTOList ) {
            list.add( toPoint( pointDTO ) );
        }

        return list;
    }

    private Integer pointMemberId(Point point) {
        if ( point == null ) {
            return null;
        }
        Member member = point.getMember();
        if ( member == null ) {
            return null;
        }
        Integer id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Member pointDTOToMember(PointDTO pointDTO) {
        if ( pointDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( pointDTO.getMemIdx() );

        return member;
    }
}
