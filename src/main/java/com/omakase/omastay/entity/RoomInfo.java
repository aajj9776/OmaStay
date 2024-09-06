package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.PayStatus;
import com.omakase.omastay.entity.enumurate.RiStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.tomcat.util.http.parser.Host;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room_info")
@ToString(exclude = {"hostInfo"})
public class RoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ri_idx", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Column(name = "ri_name", nullable = false, length = 100)
    private String riName;

    @Column(name = "ri_type", nullable = false, length = 100)
    private String riType;

    @Column(name = "ri_count", nullable = false)
    private int riCount;

    @Column(name = "ri_intro", nullable = false, length = 500)
    private String riIntro;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ri_status", nullable = false)
    private RiStatus riStatus;

    @Column(name = "ri_person", nullable = false)
    private int riPerson;

    @Column(name = "ri_none", length = 100)
    private String riNone;
}