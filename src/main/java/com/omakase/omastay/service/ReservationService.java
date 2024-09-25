package com.omakase.omastay.service;

import java.time.LocalDateTime;

import com.omakase.omastay.entity.enumurate.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.IssuedCouponDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.HostReservationDTO;
import com.omakase.omastay.entity.Coupon;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Payment;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.mapper.ReservationMapper;
import com.omakase.omastay.mapper.RoomInfoMapper;
import com.omakase.omastay.repository.PaymentRepository;
import com.omakase.omastay.repository.ReservationRepository;
import com.omakase.omastay.vo.StartEndVo;

import java.util.List;
import java.util.ArrayList;
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

    public List<HostReservationDTO> getAllRes(List<RoomInfoDTO> roomInfoDTOList) {
        List<Reservation> allReservations = new ArrayList<>();

    for (RoomInfoDTO roomInfoDTO : roomInfoDTOList) {
        RoomInfo roomInfo = RoomInfoMapper.INSTANCE.toRoomInfo(roomInfoDTO);
        List<Reservation> reservations = reservationRepository.findByRoomInfo(roomInfo);
        allReservations.addAll(reservations);
    }
        return ReservationMapper.INSTANCE.toHostReservationDTOList(allReservations);
    }

    public List<HostReservationDTO> searchRes(String resStatus, String dateValue, List<RoomInfoDTO> roomInfoDTOList) {
        List<Reservation> allReservations = new ArrayList<>();

        if (dateValue != null && !dateValue.trim().isEmpty()) {
            String[] date = dateValue.split(" ~ ");
            if (date.length < 2 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid date range format");
            }
            String startDate = date[0];
            String endDate = date[1];
            for (RoomInfoDTO roomInfoDTO : roomInfoDTOList) {
                RoomInfo roomInfo = RoomInfoMapper.INSTANCE.toRoomInfo(roomInfoDTO);
                List<Reservation> reservations = reservationRepository.searchRes(resStatus, startDate, endDate, roomInfo);
                allReservations.addAll(reservations);
            }
        } else {
            for (RoomInfoDTO roomInfoDTO : roomInfoDTOList) {
                RoomInfo roomInfo = RoomInfoMapper.INSTANCE.toRoomInfo(roomInfoDTO);
                List<Reservation> reservations = reservationRepository.searchRes(resStatus, null, null, roomInfo);
                allReservations.addAll(reservations);
            }
        }

        return ReservationMapper.INSTANCE.toHostReservationDTOList(allReservations);
    }

    // 예약 확정
    public int confirmRes(List<Integer> ids) {
        int[] idArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idArray[i] = ids.get(i);
        }

        int cnt = reservationRepository.confirmById(idArray);
        return cnt;
    }

    // 예약 취소
    public int rejectRes(List<Integer> ids) {
        int[] idArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idArray[i] = ids.get(i);
        }

        int cnt = reservationRepository.rejectById(idArray);
        return cnt;
    }

    //호스트 예약 상세
    public HostReservationDTO getHostRes(int id) {
        Reservation res = reservationRepository.findById(id).get();
        return ReservationMapper.INSTANCE.toHostReservationDTO(res);
    }

    /********** 체크 아웃 시점 이후 [확정]->[사용 완료] 예약 상태 변경 **********/
    // 30분마다 실행
    @Transactional
    @Scheduled(fixedRate = 1800000) // 30분 = 1800000 milliseconds
    public void checkAndUpdateExpiredStatus() {
        System.out.println("예약 상태 업데이트");
        reservationRepository.updateExpiredStatuses();
    }
}
