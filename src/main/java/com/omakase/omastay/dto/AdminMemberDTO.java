package com.omakase.omastay.dto;

import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.vo.UserProfileVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminMemberDTO {
    private Integer id;
    private String adId;
    private UserProfileVo adminProfile = new UserProfileVo();
    private Integer adAuth;
    private String adNone;

    public AdminMemberDTO(AdminMember adminMember) {
        this.id = adminMember.getId();
        this.adId = adminMember.getAdId();
        this.adminProfile = adminMember.getAdminProfile();
        this.adAuth = adminMember.getAdAuth();
        this.adNone = adminMember.getAdNone();
    }

    @QueryProjection
    public AdminMemberDTO(Integer id, String adId, UserProfileVo adminProfile, Integer adAuth, String adNone) {
        this.id = id;
        this.adId = adId;
        this.adminProfile = adminProfile;
        this.adAuth = adAuth;
        this.adNone = adNone;
    }

}