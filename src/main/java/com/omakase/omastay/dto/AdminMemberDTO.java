package com.omakase.omastay.dto;

import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.vo.UserProfileVo;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminMemberDTO {
    private int id;
    private String adId;
    private UserProfileVo adminProfile;
    private int adAuth;
    private String adNone;

    public AdminMemberDTO(AdminMember adminMember) {
        this.id = adminMember.getId();
        this.adId = adminMember.getAdId();
        this.adminProfile = adminMember.getAdminProfile();
        this.adAuth = adminMember.getAdAuth();
        this.adNone = adminMember.getAdNone();
    }

    @QueryProjection
    public AdminMemberDTO(int id, String adId, UserProfileVo adminProfile, int adAuth, String adNone) {
        this.id = id;
        this.adId = adId;
        this.adminProfile = adminProfile;
        this.adAuth = adAuth;
        this.adNone = adNone;
    }

}