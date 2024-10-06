package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "grade.id", target = "GIdx")
    @Mapping(source = "memberProfile.email", target = "memberProfile.email")
    @Mapping(source = "memberProfile.pw", target = "memberProfile.pw")
    @Mapping(source = "memberProfile.status", target = "memberProfile.status")
    @Mapping(target = "reservations", ignore = true)
    MemberDTO toMemberDTO(Member member);

    @Mapping(source = "GIdx", target = "grade.id")
    @Mapping(source = "memberProfile.email", target = "memberProfile.email")
    @Mapping(source = "memberProfile.pw", target = "memberProfile.pw")
    @Mapping(source = "memberProfile.status", target = "memberProfile.status")
    @Mapping(target = "reservations", ignore = true)
    Member toMember(MemberDTO memberDTO);

    List<MemberDTO> toMemberDTOList(List<Member> memberList);

    List<Member> toMemberList(List<MemberDTO> memberDTOList);
}