package com.omakase.omastay.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.mapper.MemberMapper;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.repository.PaymentRepository;
import com.omakase.omastay.repository.ReservationRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class MyPageService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public MemberDTO getMemberInfo(int memberId) {

        Optional<Member> res = memberRepository.findMemberWithReservations(memberId);

        MemberDTO memberDTO = MemberMapper.INSTANCE.toMemberDTO(res.get());

        return memberDTO;
    }

    public PaymentDTO getPaymentForReservation(Integer id) {
        //  // Reservation을 조회하면서 Payment를 함께 가져오는 로직
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + id));

        // Reservation을 통해 Payment를 조회
        Payment payment = reservation.getPayment();

        return PaymentMapper.INSTANCE.toPaymentDTO(payment);

    }


    
}
