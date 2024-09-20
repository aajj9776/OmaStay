package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.InquiryDTO;
import com.omakase.omastay.entity.Inquiry;
import com.omakase.omastay.entity.Member;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class InquiryMapperImpl implements InquiryMapper {

    @Override
    public InquiryDTO toInquiryDTO(Inquiry inquiry) {
        if ( inquiry == null ) {
            return null;
        }

        InquiryDTO inquiryDTO = new InquiryDTO();

        inquiryDTO.setMIdx( inquiryMemberId( inquiry ) );
        inquiryDTO.setId( inquiry.getId() );
        inquiryDTO.setIqTitle( inquiry.getIqTitle() );
        inquiryDTO.setIqContent( inquiry.getIqContent() );
        inquiryDTO.setIqWriter( inquiry.getIqWriter() );
        inquiryDTO.setFileName( inquiry.getFileName() );
        inquiryDTO.setIqDate( inquiry.getIqDate() );
        inquiryDTO.setIqStatus( inquiry.getIqStatus() );
        inquiryDTO.setCStatus( inquiry.getCStatus() );
        inquiryDTO.setIqAuth( inquiry.getIqAuth() );
        inquiryDTO.setIqNone( inquiry.getIqNone() );

        return inquiryDTO;
    }

    @Override
    public Inquiry toInquiry(InquiryDTO inquiryDTO) {
        if ( inquiryDTO == null ) {
            return null;
        }

        Inquiry inquiry = new Inquiry();

        inquiry.setMember( inquiryDTOToMember( inquiryDTO ) );
        inquiry.setId( inquiryDTO.getId() );
        inquiry.setIqTitle( inquiryDTO.getIqTitle() );
        inquiry.setIqContent( inquiryDTO.getIqContent() );
        inquiry.setIqWriter( inquiryDTO.getIqWriter() );
        inquiry.setFileName( inquiryDTO.getFileName() );
        inquiry.setIqDate( inquiryDTO.getIqDate() );
        inquiry.setIqStatus( inquiryDTO.getIqStatus() );
        inquiry.setCStatus( inquiryDTO.getCStatus() );
        inquiry.setIqAuth( inquiryDTO.getIqAuth() );
        inquiry.setIqNone( inquiryDTO.getIqNone() );

        return inquiry;
    }

    @Override
    public List<InquiryDTO> toInquiryDTOList(List<Inquiry> inquiryList) {
        if ( inquiryList == null ) {
            return null;
        }

        List<InquiryDTO> list = new ArrayList<InquiryDTO>( inquiryList.size() );
        for ( Inquiry inquiry : inquiryList ) {
            list.add( toInquiryDTO( inquiry ) );
        }

        return list;
    }

    @Override
    public List<Inquiry> toInquiryList(List<InquiryDTO> inquiryDTOList) {
        if ( inquiryDTOList == null ) {
            return null;
        }

        List<Inquiry> list = new ArrayList<Inquiry>( inquiryDTOList.size() );
        for ( InquiryDTO inquiryDTO : inquiryDTOList ) {
            list.add( toInquiry( inquiryDTO ) );
        }

        return list;
    }

    private Integer inquiryMemberId(Inquiry inquiry) {
        if ( inquiry == null ) {
            return null;
        }
        Member member = inquiry.getMember();
        if ( member == null ) {
            return null;
        }
        Integer id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Member inquiryDTOToMember(InquiryDTO inquiryDTO) {
        if ( inquiryDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( inquiryDTO.getMIdx() );

        return member;
    }
}
