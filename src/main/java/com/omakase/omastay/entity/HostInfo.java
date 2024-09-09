package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.vo.AddressVo;
import com.omakase.omastay.vo.HostContactInfoVo;
import com.omakase.omastay.vo.HostOwnerInfoVo;
import com.omakase.omastay.entity.enumurate.HStatus;
import com.omakase.omastay.entity.enumurate.HStep;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "host_info")
@ToString(exclude = "adminMember")
public class HostInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "h_idx", nullable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_idx", referencedColumnName = "ad_idx")
    private AdminMember adminMember = new AdminMember();

    //주소
    //우편번호, 주소, 상세주소
    @Embedded
    private AddressVo hostAddress = new AddressVo();

    @Column(name = "region", length = 100)
    private String region;

    // 모델, 호텔/리조트, 펜션/풀빌라, 게하/한옥
    @Enumerated
    @Column(name = "h_cate", nullable = false)
    private HCate hCate;

    @Column(name = "x_axis", length = 100)
    private String xAxis;

    @Column(name = "y_axis", length = 100)
    private String yAxis;

    //호스트 소유주 정보
    //호스트명, 호스트 소개
    @Embedded
    private HostOwnerInfoVo hostOwnerInfo = new HostOwnerInfoVo();

    //호스트 담당자
    //연결자명, 연결자 이메일
    @Embedded
    private HostContactInfoVo hostContactInfo = new HostContactInfoVo();

    @Column(name = "h_url", length = 100)
    private String hurl;

    @Column(name = "checkin", length = 100)
    private String checkin;

    @Column(name = "checkout", length = 100)
    private String checkout;

    @Column(name = "directions", length = 100)
    private String directions;

    @Column(name = "rules", length = 100)
    private String rules;

    @Column(name = "price_add", length = 100)
    private String priceAdd;

    //신청 승인 반려 해제
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "h_status", nullable = true)
    private HStatus hStatus;

    //마이페이지 숙소 이용규칙 객실
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "h_step", nullable = false)
    private HStep hStep;

    @Column(name = "h_none", length = 100)
    private String hNone;

    //숙소명
    @Column(name = "h_name", nullable = false, length = 100)
    private String hname;

    //대표번호
    @Column(name = "h_phone", nullable = false, length = 100)
    private String hphone;

}