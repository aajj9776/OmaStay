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
@Table(name = "host_facilities")
@ToString(exclude = {"facilities", "hostInfo"})
public class HostFacilities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "f_idx", referencedColumnName = "f_idx")
    private Facilities facilities = new Facilities();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "h_idx", referencedColumnName = "h_idx")
    private HostInfo hostInfo = new HostInfo();

    @Column(name = "room_none", length = 100)
    private String roomNone;
}