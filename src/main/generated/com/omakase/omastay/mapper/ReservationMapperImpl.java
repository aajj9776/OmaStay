package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.NonMember;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.PayMethod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:42+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
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
        reservationDTO.setStartEndVo( reservation.getStartEndVo() );
        reservationDTO.setId( reservation.getId() );
        reservationDTO.setResNum( reservation.getResNum() );
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
        if ( reservationDTO.getPayment() != null ) {
            if ( reservation.getPayment() == null ) {
                reservation.setPayment( new Payment() );
            }
            paymentDTOToPayment( reservationDTO.getPayment(), reservation.getPayment() );
        }
        if ( reservation.getPayment() == null ) {
            reservation.setPayment( new Payment() );
        }
        reservationDTOToPayment( reservationDTO, reservation.getPayment() );
        reservation.setStartEndVo( reservationDTO.getStartEndVo() );
        reservation.setId( reservationDTO.getId() );
        reservation.setResNum( reservationDTO.getResNum() );
        reservation.setResName( reservationDTO.getResName() );
        reservation.setResEmail( reservationDTO.getResEmail() );
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

    protected void paymentDTOToPayment(PaymentDTO paymentDTO, Payment mappingTarget) {
        if ( paymentDTO == null ) {
            return;
        }

        mappingTarget.setId( paymentDTO.getId() );
        mappingTarget.setPayStatus( paymentDTO.getPayStatus() );
        if ( paymentDTO.getPayMethod() != null ) {
            mappingTarget.setPayMethod( Enum.valueOf( PayMethod.class, paymentDTO.getPayMethod() ) );
        }
        else {
            mappingTarget.setPayMethod( null );
        }
        mappingTarget.setPayContent( paymentDTO.getPayContent() );
        mappingTarget.setNsalePrice( paymentDTO.getNsalePrice() );
        mappingTarget.setCancelContent( paymentDTO.getCancelContent() );
        mappingTarget.setPayDate( paymentDTO.getPayDate() );
        mappingTarget.setCancelDate( paymentDTO.getCancelDate() );
        mappingTarget.setPaymentKey( paymentDTO.getPaymentKey() );
        mappingTarget.setPayNone( paymentDTO.getPayNone() );
    }

    protected void reservationDTOToPayment(ReservationDTO reservationDTO, Payment mappingTarget) {
        if ( reservationDTO == null ) {
            return;
        }

        mappingTarget.setId( reservationDTO.getPayIdx() );
    }
}
