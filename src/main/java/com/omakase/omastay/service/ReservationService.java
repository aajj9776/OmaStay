package com.omakase.omastay.service;

import java.time.LocalDateTime;

import com.omakase.omastay.entity.enumurate.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.mapper.ReservationMapper;
import com.omakase.omastay.repository.PaymentRepository;
import com.omakase.omastay.repository.ReservationRepository;
import com.omakase.omastay.vo.StartEndVo;
import java.util.Optional;
import jakarta.transaction.Transactional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public PaymentDTO insertPaymentInfo(PaymentDTO payment) {
        System.out.println("얍"+ payment);

        // 결제 ID를 기준으로 비관적 락을 걸고 기존 결제 정보 조회
        payment.setId(1);
        Payment checkPay = paymentRepository.findByPaymentKeyWithLock(payment.getPaymentKey());

        // 이미 결제가 완료된 경우 중복 처리 방지
        if (checkPay != null) {
            throw new IllegalStateException("이미 결제가 완료된 상태입니다.");
        }

        Payment res = PaymentMapper.INSTANCE.toPayment(payment);
        System.out.println("여기뭐가나와요?"+ res);
        res.setIssuedCoupon(null);
        res.setPoint(null);

        if( payment.getNsalePrice() == null ){
            res.setNsalePrice("0");
        }

        res.setPayStatus(PayStatus.PAY);
        res.setPayDate(LocalDateTime.now());

        Payment pay = paymentRepository.save(res);

        PaymentDTO dto = PaymentMapper.INSTANCE.toPaymentDTO(pay);
        return dto;
    }

    @Transactional
    public ReservationDTO insertReservationInfo(ReservationDTO reservationDTO, PaymentDTO paymentDTO) {

        Reservation res = ReservationMapper.INSTANCE.toReservation(reservationDTO);

        res.setResPrice(Integer.parseInt(paymentDTO.getAmount()));
        Member member = new Member();
        member.setId(2);
        res.setMember(member);
        res.setResPerson(2);
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.setId(1);
        res.setRoomInfo(roomInfo);
        StartEndVo startEndVo = new StartEndVo();
        startEndVo.setStart(LocalDateTime.now());
        startEndVo.setEnd(LocalDateTime.now().plusDays(1));
        res.setStartEndVo(startEndVo);
        res.setResStatus(ResStatus.COMPLETED);
        res.setNonMember(null);

        Reservation result = reservationRepository.save(res);
        ReservationDTO dto = ReservationMapper.INSTANCE.toReservationDTO(result);
        return dto;
    }

    public ReservationDTO getReservation(int resIdx ) {
        Reservation res = reservationRepository.findById(resIdx).get();
        res.setResStatus(ResStatus.CANCELLED);
        Reservation result = reservationRepository.save(res);
        ReservationDTO dto = ReservationMapper.INSTANCE.toReservationDTO(result);
        return dto;
    }

}
