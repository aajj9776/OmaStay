package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReservationDTO;
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
    @Mapping(source = "startEndVo", target = "startEndVo")
    ReservationDTO toReservationDTO(Reservation reservation);

    @Mapping(source = "roomIdx", target = "roomInfo.id")
    @Mapping(source = "memIdx", target = "member.id")
    @Mapping(source = "nonIdx", target = "nonMember.id")
    @Mapping(source = "payIdx", target = "payment.id")
    @Mapping(source = "startEndVo", target = "startEndVo")
    Reservation toReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> toReservationDTOList(List<Reservation> reservationList);

    List<Reservation> toReservationList(List<ReservationDTO> reservationDTOList);
}