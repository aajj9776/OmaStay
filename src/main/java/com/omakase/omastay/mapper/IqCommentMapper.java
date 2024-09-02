package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.IqCommentDTO;
import com.omakase.omastay.entity.IqComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IqCommentMapper {
    IqCommentMapper INSTANCE = Mappers.getMapper(IqCommentMapper.class);

    @Mapping(source = "inquiry.id", target = "iqIdx")
    IqCommentDTO toIqCommentDTO(IqComment iqComment);

    @Mapping(source = "iqIdx", target = "inquiry.id")
    IqComment toIqComment(IqCommentDTO iqCommentDTO);

    List<IqCommentDTO> toIqCommentDTOList(List<IqComment> iqCommentList);

    List<IqComment> toIqCommentList(List<IqCommentDTO> iqCommentDTOList);
}