package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.VisitorDTO;
import com.omakase.omastay.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface VisitorMapper {
    VisitorMapper INSTANCE = Mappers.getMapper(VisitorMapper.class);

    VisitorDTO toVisitorDTO(Visitor visitor);

    Visitor toVisitor(VisitorDTO visitorDTO);

    List<VisitorDTO> toVisitorDTOList(List<Visitor> visitorList);

    List<Visitor> toVisitorList(List<VisitorDTO> visitorDTOList);
}