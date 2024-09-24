package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.FacilitiesDTO;
import com.omakase.omastay.entity.Facilities;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:37+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class FacilitiesMapperImpl implements FacilitiesMapper {

    @Override
    public FacilitiesDTO toFacilitiesDTO(Facilities facilities) {
        if ( facilities == null ) {
            return null;
        }

        FacilitiesDTO facilitiesDTO = new FacilitiesDTO();

        facilitiesDTO.setId( facilities.getId() );
        facilitiesDTO.setFCate( facilities.getFCate() );
        facilitiesDTO.setFNone( facilities.getFNone() );

        return facilitiesDTO;
    }

    @Override
    public Facilities toFacilities(FacilitiesDTO facilitiesDTO) {
        if ( facilitiesDTO == null ) {
            return null;
        }

        Facilities facilities = new Facilities();

        facilities.setId( facilitiesDTO.getId() );
        facilities.setFCate( facilitiesDTO.getFCate() );
        facilities.setFNone( facilitiesDTO.getFNone() );

        return facilities;
    }

    @Override
    public List<FacilitiesDTO> toFacilitiesDTOList(List<Facilities> facilitiesList) {
        if ( facilitiesList == null ) {
            return null;
        }

        List<FacilitiesDTO> list = new ArrayList<FacilitiesDTO>( facilitiesList.size() );
        for ( Facilities facilities : facilitiesList ) {
            list.add( toFacilitiesDTO( facilities ) );
        }

        return list;
    }

    @Override
    public List<Facilities> toFacilitiesList(List<FacilitiesDTO> facilitiesDTOList) {
        if ( facilitiesDTOList == null ) {
            return null;
        }

        List<Facilities> list = new ArrayList<Facilities>( facilitiesDTOList.size() );
        for ( FacilitiesDTO facilitiesDTO : facilitiesDTOList ) {
            list.add( toFacilities( facilitiesDTO ) );
        }

        return list;
    }
}
