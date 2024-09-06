package com.omakase.omastay.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room_facilities")
@ToString(exclude = {"facilities", "roomInfo"})
public class RoomFacilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_idx", referencedColumnName = "f_idx")
    private Facilities facilities = new Facilities();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ri_idx", referencedColumnName = "ri_idx")
    private RoomInfo roomInfo = new RoomInfo();

    @Column(name = "room_none", length = 100)
    private String roomNone;
}