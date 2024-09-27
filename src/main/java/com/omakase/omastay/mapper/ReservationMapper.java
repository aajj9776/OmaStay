package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.custom.HostReservationDTO;
import com.omakase.omastay.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(source = "roomInfo.id", target = "roomIdx")
    @Mapping(source = "member.id", target = "memIdx")
    @Mapping(source = "nonMember.id", target = "nonIdx")
    @Mapping(source = "payment.id", target = "payIdx")
    @Mapping(target = "payment", ignore = true)
    ReservationDTO toReservationDTO(Reservation reservation);

    @Mapping(source = "roomIdx", target = "roomInfo.id")
    @Mapping(source = "memIdx", target = "member.id")
    @Mapping(source = "nonIdx", target = "nonMember.id")
    @Mapping(source = "payIdx", target = "payment.id")
    Reservation toReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> toReservationDTOList(List<Reservation> reservationList);

    List<Reservation> toReservationList(List<ReservationDTO> reservationDTOList);

    @Mapping(source = "roomInfo.id", target = "roomIdx")
    @Mapping(source = "roomInfo.roomName", target = "roomName")
    @Mapping(source = "member.id", target = "memIdx")
    @Mapping(source = "nonMember.id", target = "nonIdx")
    @Mapping(source = "payment.id", target = "payIdx")
    HostReservationDTO toHostReservationDTO(Reservation reservation);

    List<HostReservationDTO> toHostReservationDTOList(List<Reservation> reservationList);
}