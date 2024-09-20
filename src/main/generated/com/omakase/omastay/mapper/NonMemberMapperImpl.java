package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.NonMemberDTO;
import com.omakase.omastay.entity.NonMember;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-20T10:12:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class NonMemberMapperImpl implements NonMemberMapper {

    @Override
    public NonMemberDTO toNonMemberDTO(NonMember nonMember) {
        if ( nonMember == null ) {
            return null;
        }

        NonMemberDTO nonMemberDTO = new NonMemberDTO();

        nonMemberDTO.setId( nonMember.getId() );
        nonMemberDTO.setNonName( nonMember.getNonName() );
        nonMemberDTO.setNonPhone( nonMember.getNonPhone() );
        nonMemberDTO.setNonEmail( nonMember.getNonEmail() );
        nonMemberDTO.setNonNone( nonMember.getNonNone() );

        return nonMemberDTO;
    }

    @Override
    public NonMember toNonMember(NonMemberDTO nonMemberDTO) {
        if ( nonMemberDTO == null ) {
            return null;
        }

        NonMember nonMember = new NonMember();

        nonMember.setId( nonMemberDTO.getId() );
        nonMember.setNonName( nonMemberDTO.getNonName() );
        nonMember.setNonPhone( nonMemberDTO.getNonPhone() );
        nonMember.setNonEmail( nonMemberDTO.getNonEmail() );
        nonMember.setNonNone( nonMemberDTO.getNonNone() );

        return nonMember;
    }

    @Override
    public List<NonMemberDTO> toNonMemberDTOList(List<NonMember> nonMemberList) {
        if ( nonMemberList == null ) {
            return null;
        }

        List<NonMemberDTO> list = new ArrayList<NonMemberDTO>( nonMemberList.size() );
        for ( NonMember nonMember : nonMemberList ) {
            list.add( toNonMemberDTO( nonMember ) );
        }

        return list;
    }

    @Override
    public List<NonMember> toNonMemberList(List<NonMemberDTO> nonMemberDTOList) {
        if ( nonMemberDTOList == null ) {
            return null;
        }

        List<NonMember> list = new ArrayList<NonMember>( nonMemberDTOList.size() );
        for ( NonMemberDTO nonMemberDTO : nonMemberDTOList ) {
            list.add( toNonMember( nonMemberDTO ) );
        }

        return list;
    }
}
