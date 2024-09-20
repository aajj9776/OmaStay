package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.entity.Grade;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.vo.UserProfileVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-20T10:12:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO toMemberDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberProfile( userProfileVoToUserProfileVo( member.getMemberProfile() ) );
        memberDTO.setGIdx( memberGradeId( member ) );
        memberDTO.setId( member.getId() );
        memberDTO.setMemPhone( member.getMemPhone() );
        memberDTO.setMemName( member.getMemName() );
        memberDTO.setMemEmailCheck( member.getMemEmailCheck() );
        memberDTO.setMemBirth( member.getMemBirth() );
        memberDTO.setMemJoinDate( member.getMemJoinDate() );
        memberDTO.setMemSocial( member.getMemSocial() );
        memberDTO.setAddressVo( member.getAddressVo() );
        memberDTO.setMemGender( member.getMemGender() );
        memberDTO.setAccessToken( member.getAccessToken() );
        memberDTO.setRefreshToken( member.getRefreshToken() );
        memberDTO.setMemNone( member.getMemNone() );

        return memberDTO;
    }

    @Override
    public Member toMember(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setGrade( memberDTOToGrade( memberDTO ) );
        member.setMemberProfile( userProfileVoToUserProfileVo1( memberDTO.getMemberProfile() ) );
        member.setId( memberDTO.getId() );
        member.setMemPhone( memberDTO.getMemPhone() );
        member.setMemName( memberDTO.getMemName() );
        member.setMemEmailCheck( memberDTO.getMemEmailCheck() );
        member.setMemBirth( memberDTO.getMemBirth() );
        member.setMemJoinDate( memberDTO.getMemJoinDate() );
        member.setMemSocial( memberDTO.getMemSocial() );
        member.setAddressVo( memberDTO.getAddressVo() );
        member.setMemGender( memberDTO.getMemGender() );
        member.setAccessToken( memberDTO.getAccessToken() );
        member.setRefreshToken( memberDTO.getRefreshToken() );
        member.setMemNone( memberDTO.getMemNone() );

        return member;
    }

    @Override
    public List<MemberDTO> toMemberDTOList(List<Member> memberList) {
        if ( memberList == null ) {
            return null;
        }

        List<MemberDTO> list = new ArrayList<MemberDTO>( memberList.size() );
        for ( Member member : memberList ) {
            list.add( toMemberDTO( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toMemberList(List<MemberDTO> memberDTOList) {
        if ( memberDTOList == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( memberDTOList.size() );
        for ( MemberDTO memberDTO : memberDTOList ) {
            list.add( toMember( memberDTO ) );
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

    private Integer memberGradeId(Member member) {
        if ( member == null ) {
            return null;
        }
        Grade grade = member.getGrade();
        if ( grade == null ) {
            return null;
        }
        Integer id = grade.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Grade memberDTOToGrade(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Grade grade = new Grade();

        grade.setId( memberDTO.getGIdx() );

        return grade;
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
