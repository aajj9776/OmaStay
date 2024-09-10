package com.omakase.omastay.entity;

import com.omakase.omastay.entity.enumurate.RoomStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room_info")
@ToString(exclude = {"hostInfo"})
public class RoomInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    @Column(name = "room_type", nullable = false, length = 100)
    private String roomType;

    @Column(name = "room_intro", nullable = false, length = 500)
    private String roomIntro;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "room_status", nullable = false)
    private RoomStatus roomStatus;

    @Column(name = "room_person", nullable = false)
    private int roomPerson;

    @Column(name = "room_none", length = 100)
    private String roomNone;
}