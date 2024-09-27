package com.omakase.omastay.dto;

import java.time.LocalDateTime;

import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.Gender;
import com.omakase.omastay.entity.enumurate.Social;
import com.omakase.omastay.vo.AddressVo;
import com.omakase.omastay.vo.UserProfileVo;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberDTO {
    private Integer id;
    private Integer gIdx;
    private UserProfileVo memberProfile = new UserProfileVo();
    private String memPhone;
    private String memName;
    private BooleanStatus memEmailCheck;
    private String memBirth;
    private LocalDateTime memJoinDate;
    private Social memSocial;
    private AddressVo addressVo = new AddressVo();
    private Gender memGender;
    private String accessToken;
    private String refreshToken;
    private String memNone;
    private List<ReservationDTO> reservations;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.gIdx = member.getGrade() != null ? member.getGrade().getId() : null;
        this.memberProfile = member.getMemberProfile();
        this.memPhone = member.getMemPhone();
        this.memName = member.getMemName();
        this.memEmailCheck = member.getMemEmailCheck();
        this.memBirth = member.getMemBirth();
        this.memJoinDate = member.getMemJoinDate();
        this.memSocial = member.getMemSocial();
        this.addressVo = member.getAddressVo();
        this.memGender = member.getMemGender();
        this.accessToken = member.getAccessToken();
        this.refreshToken = member.getRefreshToken();
        this.memNone = member.getMemNone();
    }

    @QueryProjection
    public MemberDTO(Integer id, Integer gIdx, UserProfileVo memberProfile, String memPhone, String memName, BooleanStatus memEmailCheck, String memBirth, LocalDateTime memJoinDate, Social memSocial, AddressVo addressVo, Gender memGender, String accessToken, String refreshToken, String memNone) {
        this.id = id;
        this.gIdx = gIdx;
        this.memberProfile = memberProfile;
        this.memPhone = memPhone;
        this.memName = memName;
        this.memEmailCheck = memEmailCheck;
        this.memBirth = memBirth;
        this.memJoinDate = memJoinDate;
        this.memSocial = memSocial;
        this.addressVo = addressVo;
        this.memGender = memGender;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.memNone = memNone;
    }
}