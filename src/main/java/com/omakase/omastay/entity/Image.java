package com.omakase.omastay.entity;

import com.omakase.omastay.vo.FileImageNameVo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.ImgCate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image")
@ToString(exclude = {"roomInfo", "hostInfo"})
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ri_idx", referencedColumnName = "ri_idx")
    private RoomInfo roomInfo = new RoomInfo();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "img_cate")
    private ImgCate imgCate;

    //파일이름, 파일원본이름
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fName", column = @Column(name = "f_name", nullable = false, length = 200)),
            @AttributeOverride(name = "oName", column = @Column(name = "ori_name", nullable = false, length = 200))
    })
    private FileImageNameVo imgName = new FileImageNameVo();

    //FALSE:삭제, TRUE:존재
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "img_status", nullable = false)
    private BooleanStatus imgStatus;

    @Column(name = "img_none", length = 100)
    private String imgNone;
}