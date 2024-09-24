package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.NonMember;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:36+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ReservationDTO toReservationDTO(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setRoomIdx( reservationRoomInfoId( reservation ) );
        reservationDTO.setMemIdx( reservationMemberId( reservation ) );
        reservationDTO.setNonIdx( reservationNonMemberId( reservation ) );
        reservationDTO.setPayIdx( reservationPaymentId( reservation ) );
        reservationDTO.setId( reservation.getId() );
        reservationDTO.setResNum( reservation.getResNum() );
        reservationDTO.setStartEndVo( reservation.getStartEndVo() );
        reservationDTO.setResPerson( reservation.getResPerson() );
        reservationDTO.setResPrice( reservation.getResPrice() );
        reservationDTO.setResStatus( reservation.getResStatus() );
        reservationDTO.setResName( reservation.getResName() );
        reservationDTO.setResEmail( reservation.getResEmail() );
        reservationDTO.setResNone( reservation.getResNone() );

        return reservationDTO;
    }

    @Override
    public Reservation toReservation(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setRoomInfo( reservationDTOToRoomInfo( reservationDTO ) );
        reservation.setMember( reservationDTOToMember( reservationDTO ) );
        reservation.setNonMember( reservationDTOToNonMember( reservationDTO ) );
        reservation.setPayment( reservationDTOToPayment( reservationDTO ) );
        reservation.setId( reservationDTO.getId() );
        reservation.setResNum( reservationDTO.getResNum() );
        reservation.setResName( reservationDTO.getResName() );
        reservation.setResEmail( reservationDTO.getResEmail() );
        reservation.setStartEndVo( reservationDTO.getStartEndVo() );
        reservation.setResPerson( reservationDTO.getResPerson() );
        reservation.setResPrice( reservationDTO.getResPrice() );
        reservation.setResStatus( reservationDTO.getResStatus() );
        reservation.setResNone( reservationDTO.getResNone() );

        return reservation;
    }

    @Override
    public List<ReservationDTO> toReservationDTOList(List<Reservation> reservationList) {
        if ( reservationList == null ) {
            return null;
        }

        List<ReservationDTO> list = new ArrayList<ReservationDTO>( reservationList.size() );
        for ( Reservation reservation : reservationList ) {
            list.add( toReservationDTO( reservation ) );
        }

        return list;
    }

    @Override
    public List<Reservation> toReservationList(List<ReservationDTO> reservationDTOList) {
        if ( reservationDTOList == null ) {
            return null;
        }

        List<Reservation> list = new ArrayList<Reservation>( reservationDTOList.size() );
        for ( ReservationDTO reservationDTO : reservationDTOList ) {
            list.add( toReservation( reservationDTO ) );
        }

        return list;
    }

    private Integer reservationRoomInfoId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        RoomInfo roomInfo = reservation.getRoomInfo();
        if ( roomInfo == null ) {
            return null;
        }
        Integer id = roomInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer reservationMemberId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        Member member = reservation.getMember();
        if ( member == null ) {
            return null;
        }
        Integer id = member.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer reservationNonMemberId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        NonMember nonMember = reservation.getNonMember();
        if ( nonMember == null ) {
            return null;
        }
        Integer id = nonMember.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer reservationPaymentId(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }
        Payment payment = reservation.getPayment();
        if ( payment == null ) {
            return null;
        }
        Integer id = payment.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected RoomInfo reservationDTOToRoomInfo(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        RoomInfo roomInfo = new RoomInfo();

        roomInfo.setId( reservationDTO.getRoomIdx() );

        return roomInfo;
    }

    protected Member reservationDTOToMember(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( reservationDTO.getMemIdx() );

        return member;
    }

    protected NonMember reservationDTOToNonMember(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        NonMember nonMember = new NonMember();

        nonMember.setId( reservationDTO.getNonIdx() );

        return nonMember;
    }

    protected Payment reservationDTOToPayment(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setId( reservationDTO.getPayIdx() );

        return payment;
    }
}
