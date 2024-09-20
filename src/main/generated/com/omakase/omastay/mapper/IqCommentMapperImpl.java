package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.IqCommentDTO;
import com.omakase.omastay.entity.Inquiry;
import com.omakase.omastay.entity.IqComment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class IqCommentMapperImpl implements IqCommentMapper {

    @Override
    public IqCommentDTO toIqCommentDTO(IqComment iqComment) {
        if ( iqComment == null ) {
            return null;
        }

        IqCommentDTO iqCommentDTO = new IqCommentDTO();

        iqCommentDTO.setIqIdx( iqCommentInquiryId( iqComment ) );
        iqCommentDTO.setId( iqComment.getId() );
        iqCommentDTO.setIqcTitle( iqComment.getIqcTitle() );
        iqCommentDTO.setIqcContent( iqComment.getIqcContent() );
        iqCommentDTO.setIqcDate( iqComment.getIqcDate() );
        iqCommentDTO.setIqcNone( iqComment.getIqcNone() );

        return iqCommentDTO;
    }

    @Override
    public IqComment toIqComment(IqCommentDTO iqCommentDTO) {
        if ( iqCommentDTO == null ) {
            return null;
        }

        IqComment iqComment = new IqComment();

        iqComment.setInquiry( iqCommentDTOToInquiry( iqCommentDTO ) );
        iqComment.setId( iqCommentDTO.getId() );
        iqComment.setIqcTitle( iqCommentDTO.getIqcTitle() );
        iqComment.setIqcContent( iqCommentDTO.getIqcContent() );
        iqComment.setIqcDate( iqCommentDTO.getIqcDate() );
        iqComment.setIqcNone( iqCommentDTO.getIqcNone() );

        return iqComment;
    }

    @Override
    public List<IqCommentDTO> toIqCommentDTOList(List<IqComment> iqCommentList) {
        if ( iqCommentList == null ) {
            return null;
        }

        List<IqCommentDTO> list = new ArrayList<IqCommentDTO>( iqCommentList.size() );
        for ( IqComment iqComment : iqCommentList ) {
            list.add( toIqCommentDTO( iqComment ) );
        }

        return list;
    }

    @Override
    public List<IqComment> toIqCommentList(List<IqCommentDTO> iqCommentDTOList) {
        if ( iqCommentDTOList == null ) {
            return null;
        }

        List<IqComment> list = new ArrayList<IqComment>( iqCommentDTOList.size() );
        for ( IqCommentDTO iqCommentDTO : iqCommentDTOList ) {
            list.add( toIqComment( iqCommentDTO ) );
        }

        return list;
    }

    private Integer iqCommentInquiryId(IqComment iqComment) {
        if ( iqComment == null ) {
            return null;
        }
        Inquiry inquiry = iqComment.getInquiry();
        if ( inquiry == null ) {
            return null;
        }
        Integer id = inquiry.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Inquiry iqCommentDTOToInquiry(IqCommentDTO iqCommentDTO) {
        if ( iqCommentDTO == null ) {
            return null;
        }

        Inquiry inquiry = new Inquiry();

        inquiry.setId( iqCommentDTO.getIqIdx() );

        return inquiry;
    }
}
