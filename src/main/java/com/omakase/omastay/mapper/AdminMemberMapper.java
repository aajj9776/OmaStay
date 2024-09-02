package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.entity.AdminMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdminMemberMapper {
    AdminMemberMapper INSTANCE = Mappers.getMapper(AdminMemberMapper.class);

    @Mapping(source = "adminProfile.email", target = "adminProfile.email")
    @Mapping(source = "adminProfile.pw", target = "adminProfile.pw")
    @Mapping(source = "adminProfile.status", target = "adminProfile.status")
    AdminMemberDTO toAdminMemberDTO(AdminMember adminMember);

    @Mapping(source = "adminProfile.email", target = "adminProfile.email")
    @Mapping(source = "adminProfile.pw", target = "adminProfile.pw")
    @Mapping(source = "adminProfile.status", target = "adminProfile.status")
    AdminMember toAdminMember(AdminMemberDTO adminMemberDTO);

    List<AdminMemberDTO> toAdminMemberDTOList(List<AdminMember> adminMemberList);

    List<AdminMember> toAdminMemberList(List<AdminMemberDTO> adminMemberDTOList);
}