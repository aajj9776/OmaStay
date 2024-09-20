package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.vo.UserProfileVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class AdminMemberMapperImpl implements AdminMemberMapper {

    @Override
    public AdminMemberDTO toAdminMemberDTO(AdminMember adminMember) {
        if ( adminMember == null ) {
            return null;
        }

        AdminMemberDTO adminMemberDTO = new AdminMemberDTO();

        adminMemberDTO.setAdminProfile( userProfileVoToUserProfileVo( adminMember.getAdminProfile() ) );
        adminMemberDTO.setId( adminMember.getId() );
        adminMemberDTO.setAdId( adminMember.getAdId() );
        adminMemberDTO.setAdAuth( adminMember.getAdAuth() );
        adminMemberDTO.setAdNone( adminMember.getAdNone() );

        return adminMemberDTO;
    }

    @Override
    public AdminMember toAdminMember(AdminMemberDTO adminMemberDTO) {
        if ( adminMemberDTO == null ) {
            return null;
        }

        AdminMember.AdminMemberBuilder adminMember = AdminMember.builder();

        adminMember.adminProfile( userProfileVoToUserProfileVo1( adminMemberDTO.getAdminProfile() ) );
        adminMember.id( adminMemberDTO.getId() );
        adminMember.adId( adminMemberDTO.getAdId() );
        adminMember.adAuth( adminMemberDTO.getAdAuth() );
        adminMember.adNone( adminMemberDTO.getAdNone() );

        return adminMember.build();
    }

    @Override
    public List<AdminMemberDTO> toAdminMemberDTOList(List<AdminMember> adminMemberList) {
        if ( adminMemberList == null ) {
            return null;
        }

        List<AdminMemberDTO> list = new ArrayList<AdminMemberDTO>( adminMemberList.size() );
        for ( AdminMember adminMember : adminMemberList ) {
            list.add( toAdminMemberDTO( adminMember ) );
        }

        return list;
    }

    @Override
    public List<AdminMember> toAdminMemberList(List<AdminMemberDTO> adminMemberDTOList) {
        if ( adminMemberDTOList == null ) {
            return null;
        }

        List<AdminMember> list = new ArrayList<AdminMember>( adminMemberDTOList.size() );
        for ( AdminMemberDTO adminMemberDTO : adminMemberDTOList ) {
            list.add( toAdminMember( adminMemberDTO ) );
        }

        return list;
    }

    protected UserProfileVo userProfileVoToUserProfileVo(UserProfileVo userProfileVo) {
        if ( userProfileVo == null ) {
            return null;
        }

        UserProfileVo.UserProfileVoBuilder userProfileVo1 = UserProfileVo.builder();

        userProfileVo1.email( userProfileVo.getEmail() );
        userProfileVo1.pw( userProfileVo.getPw() );
        userProfileVo1.status( userProfileVo.getStatus() );

        return userProfileVo1.build();
    }

    protected UserProfileVo userProfileVoToUserProfileVo1(UserProfileVo userProfileVo) {
        if ( userProfileVo == null ) {
            return null;
        }

        UserProfileVo.UserProfileVoBuilder userProfileVo1 = UserProfileVo.builder();

        userProfileVo1.email( userProfileVo.getEmail() );
        userProfileVo1.pw( userProfileVo.getPw() );
        userProfileVo1.status( userProfileVo.getStatus() );

        return userProfileVo1.build();
    }
}
