package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.NonMemberDTO;
import com.omakase.omastay.entity.NonMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NonMemberMapper {
    NonMemberMapper INSTANCE = Mappers.getMapper(NonMemberMapper.class);

    NonMemberDTO toNonMemberDTO(NonMember nonMember);

    NonMember toNonMember(NonMemberDTO nonMemberDTO);

    List<NonMemberDTO> toNonMemberDTOList(List<NonMember> nonMemberList);

    List<NonMember> toNonMemberList(List<NonMemberDTO> nonMemberDTOList);
}