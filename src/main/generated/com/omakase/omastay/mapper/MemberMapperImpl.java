package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.entity.Grade;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.enumurate.PayMethod;
import com.omakase.omastay.vo.UserProfileVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO toMemberDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberProfile( userProfileVoToUserProfileVo( member.getMemberProfile() ) );
        memberDTO.setGIdx( memberGradeId( member ) );
        memberDTO.setId( member.getId() );
        memberDTO.setMemPhone( member.getMemPhone() );
        memberDTO.setMemName( member.getMemName() );
        memberDTO.setMemEmailCheck( member.getMemEmailCheck() );
        memberDTO.setMemBirth( member.getMemBirth() );
        memberDTO.setMemJoinDate( member.getMemJoinDate() );
        memberDTO.setMemSocial( member.getMemSocial() );
        memberDTO.setAddressVo( member.getAddressVo() );
        memberDTO.setMemGender( member.getMemGender() );
        memberDTO.setAccessToken( member.getAccessToken() );
        memberDTO.setRefreshToken( member.getRefreshToken() );
        memberDTO.setMemNone( member.getMemNone() );
        memberDTO.setReservations( reservationListToReservationDTOList( member.getReservations() ) );

        return memberDTO;
    }

    @Override
    public Member toMember(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setGrade( memberDTOToGrade( memberDTO ) );
        member.setMemberProfile( userProfileVoToUserProfileVo1( memberDTO.getMemberProfile() ) );
        member.setId( memberDTO.getId() );
        member.setMemPhone( memberDTO.getMemPhone() );
        member.setMemName( memberDTO.getMemName() );
        member.setMemEmailCheck( memberDTO.getMemEmailCheck() );
        member.setMemBirth( memberDTO.getMemBirth() );
        member.setMemJoinDate( memberDTO.getMemJoinDate() );
        member.setMemSocial( memberDTO.getMemSocial() );
        member.setAddressVo( memberDTO.getAddressVo() );
        member.setMemGender( memberDTO.getMemGender() );
        member.setAccessToken( memberDTO.getAccessToken() );
        member.setRefreshToken( memberDTO.getRefreshToken() );
        member.setMemNone( memberDTO.getMemNone() );
        member.setReservations( reservationDTOListToReservationList( memberDTO.getReservations() ) );

        return member;
    }

    @Override
    public List<MemberDTO> toMemberDTOList(List<Member> memberList) {
        if ( memberList == null ) {
            return null;
        }

        List<MemberDTO> list = new ArrayList<MemberDTO>( memberList.size() );
        for ( Member member : memberList ) {
            list.add( toMemberDTO( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toMemberList(List<MemberDTO> memberDTOList) {
        if ( memberDTOList == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( memberDTOList.size() );
        for ( MemberDTO memberDTO : memberDTOList ) {
            list.add( toMember( memberDTO ) );
        }

        return list;
    }

    protected UserProfileVo userProfileVoToUserProfileVo(UserProfileVo userProfileVo) {
        if ( userProfileVo == null ) {
            return null;
        }

        UserProfileVo.UserProfileVoBuilder userProfileVo1 = UserProfileVo.builder();

        userProfileVo1.email( userProfileVo.getEmail() );
        userProfileVo1.pw( userProfileVo.getPw() );
        userProfileVo1.status( userProfileVo.getStatus() );

        return userProfileVo1.build();
    }

    private Integer memberGradeId(Member member) {
        if ( member == null ) {
            return null;
        }
        Grade grade = member.getGrade();
        if ( grade == null ) {
            return null;
        }
        Integer id = grade.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected PaymentDTO paymentToPaymentDTO(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setId( payment.getId() );
        paymentDTO.setPayStatus( payment.getPayStatus() );
        if ( payment.getPayMethod() != null ) {
            paymentDTO.setPayMethod( payment.getPayMethod().name() );
        }
        paymentDTO.setPayContent( payment.getPayContent() );
        paymentDTO.setNsalePrice( payment.getNsalePrice() );
        paymentDTO.setCancelContent( payment.getCancelContent() );
        paymentDTO.setPayDate( payment.getPayDate() );
        paymentDTO.setCancelDate( payment.getCancelDate() );
        paymentDTO.setPayNone( payment.getPayNone() );
        paymentDTO.setPaymentKey( payment.getPaymentKey() );

        return paymentDTO;
    }

    protected ReservationDTO reservationToReservationDTO(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setId( reservation.getId() );
        reservationDTO.setResNum( reservation.getResNum() );
        reservationDTO.setStartEndVo( reservation.getStartEndVo() );
        reservationDTO.setResPerson( reservation.getResPerson() );
        reservationDTO.setResPrice( reservation.getResPrice() );
        reservationDTO.setResStatus( reservation.getResStatus() );
        reservationDTO.setResName( reservation.getResName() );
        reservationDTO.setResEmail( reservation.getResEmail() );
        reservationDTO.setResNone( reservation.getResNone() );
        reservationDTO.setPayment( paymentToPaymentDTO( reservation.getPayment() ) );

        return reservationDTO;
    }

    protected List<ReservationDTO> reservationListToReservationDTOList(List<Reservation> list) {
        if ( list == null ) {
            return null;
        }

        List<ReservationDTO> list1 = new ArrayList<ReservationDTO>( list.size() );
        for ( Reservation reservation : list ) {
            list1.add( reservationToReservationDTO( reservation ) );
        }

        return list1;
    }

    protected Grade memberDTOToGrade(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Grade grade = new Grade();

        grade.setId( memberDTO.getGIdx() );

        return grade;
    }

    protected UserProfileVo userProfileVoToUserProfileVo1(UserProfileVo userProfileVo) {
        if ( userProfileVo == null ) {
            return null;
        }

        UserProfileVo.UserProfileVoBuilder userProfileVo1 = UserProfileVo.builder();

        userProfileVo1.email( userProfileVo.getEmail() );
        userProfileVo1.pw( userProfileVo.getPw() );
        userProfileVo1.status( userProfileVo.getStatus() );

        return userProfileVo1.build();
    }

    protected Payment paymentDTOToPayment(PaymentDTO paymentDTO) {
        if ( paymentDTO == null ) {
            return null;
        }

        Payment payment = new Payment();

        payment.setId( paymentDTO.getId() );
        payment.setPayStatus( paymentDTO.getPayStatus() );
        if ( paymentDTO.getPayMethod() != null ) {
            payment.setPayMethod( Enum.valueOf( PayMethod.class, paymentDTO.getPayMethod() ) );
        }
        payment.setPayContent( paymentDTO.getPayContent() );
        payment.setNsalePrice( paymentDTO.getNsalePrice() );
        payment.setCancelContent( paymentDTO.getCancelContent() );
        payment.setPayDate( paymentDTO.getPayDate() );
        payment.setCancelDate( paymentDTO.getCancelDate() );
        payment.setPaymentKey( paymentDTO.getPaymentKey() );
        payment.setPayNone( paymentDTO.getPayNone() );

        return payment;
    }

    protected Reservation reservationDTOToReservation(ReservationDTO reservationDTO) {
        if ( reservationDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setId( reservationDTO.getId() );
        reservation.setPayment( paymentDTOToPayment( reservationDTO.getPayment() ) );
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

    protected List<Reservation> reservationDTOListToReservationList(List<ReservationDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Reservation> list1 = new ArrayList<Reservation>( list.size() );
        for ( ReservationDTO reservationDTO : list ) {
            list1.add( reservationDTOToReservation( reservationDTO ) );
        }

        return list1;
    }
}
