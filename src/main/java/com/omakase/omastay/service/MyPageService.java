package com.omakase.omastay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.mapper.ReservationMapper;
import com.omakase.omastay.repository.PaymentRepository;
import com.omakase.omastay.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class MyPageService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public  List<ReservationDTO> getReservationInfo(int memIdx) {
        // Reservation을 조회하는 로직
        List<Reservation> reservation = reservationRepository.findByMemIdxAndEndBefore(memIdx);
        return  ReservationMapper.INSTANCE.toReservationDTOList(reservation);
    }

    public ReservationDTO getReservation(Integer id) {
        Reservation reservation = reservationRepository.findById(id).get();
        return ReservationMapper.INSTANCE.toReservationDTO(reservation);

    }

    public PaymentDTO getPayment(Integer payIdx) {
        Payment payment = paymentRepository.findById(payIdx).get();
        return PaymentMapper.INSTANCE.toPaymentDTO(payment);

    }

    public List<ReservationDTO> getNewReservationInfo(Integer memIdx) {
        List<Reservation> reservation = reservationRepository.findByMemIdx(memIdx);
        return  ReservationMapper.INSTANCE.toReservationDTOList(reservation);
    }

    public Page<ReservationDTO> getNewReservationInfo(Integer memberId, Pageable pageable) {
         // Page<Reservation>을 가져옴
         Page<Reservation> reservationPage = reservationRepository.findByMemberId(memberId, pageable);
        
         // Page<ReservationDTO>로 변환
         Page<ReservationDTO> reservationDTOPage = reservationPage.map(ReservationMapper.INSTANCE::toReservationDTO);
         
         // List<ReservationDTO>로 변환
         return reservationDTOPage;
    }


}
