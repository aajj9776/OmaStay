package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.VisitorDTO;
import com.omakase.omastay.entity.Visitor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class VisitorMapperImpl implements VisitorMapper {

    @Override
    public VisitorDTO toVisitorDTO(Visitor visitor) {
        if ( visitor == null ) {
            return null;
        }

        VisitorDTO visitorDTO = new VisitorDTO();

        visitorDTO.setId( visitor.getId() );
        visitorDTO.setVIp( visitor.getVIp() );
        visitorDTO.setVDate( visitor.getVDate() );
        visitorDTO.setVRefer( visitor.getVRefer() );
        visitorDTO.setVAgent( visitor.getVAgent() );
        visitorDTO.setVNone( visitor.getVNone() );

        return visitorDTO;
    }

    @Override
    public Visitor toVisitor(VisitorDTO visitorDTO) {
        if ( visitorDTO == null ) {
            return null;
        }

        Visitor visitor = new Visitor();

        visitor.setId( visitorDTO.getId() );
        visitor.setVIp( visitorDTO.getVIp() );
        visitor.setVDate( visitorDTO.getVDate() );
        visitor.setVRefer( visitorDTO.getVRefer() );
        visitor.setVAgent( visitorDTO.getVAgent() );
        visitor.setVNone( visitorDTO.getVNone() );

        return visitor;
    }

    @Override
    public List<VisitorDTO> toVisitorDTOList(List<Visitor> visitorList) {
        if ( visitorList == null ) {
            return null;
        }

        List<VisitorDTO> list = new ArrayList<VisitorDTO>( visitorList.size() );
        for ( Visitor visitor : visitorList ) {
            list.add( toVisitorDTO( visitor ) );
        }

        return list;
    }

    @Override
    public List<Visitor> toVisitorList(List<VisitorDTO> visitorDTOList) {
        if ( visitorDTOList == null ) {
            return null;
        }

        List<Visitor> list = new ArrayList<Visitor>( visitorDTOList.size() );
        for ( VisitorDTO visitorDTO : visitorDTOList ) {
            list.add( toVisitor( visitorDTO ) );
        }

        return list;
    }
}
