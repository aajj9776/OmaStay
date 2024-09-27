package com.omakase.omastay.service;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.SalesDTO;
import com.omakase.omastay.dto.custom.SalesCustomDTO;
import com.omakase.omastay.dto.custom.Top5SalesDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.HostReservationDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.Sales;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.PaymentMapper;
import com.omakase.omastay.mapper.ReservationMapper;
import com.omakase.omastay.mapper.SalesMapper;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.PaymentRepository;
import com.omakase.omastay.mapper.RoomInfoMapper;
import com.omakase.omastay.repository.ReservationRepository;
import com.omakase.omastay.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private HostInfoRepository hostInfoRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    

    /********** 예약이 사용완료가 되면 체크아웃 다음날 매출 테이블로 들어감 **********/
    @Transactional 
    @Scheduled(fixedRate = 1800000) // 30분 = 1800000 milliseconds
    public void insertSales() {

        // 현재 시간에서 하루 전 날짜를 계산
        LocalDateTime yesterday = LocalDateTime.now().minus(1, ChronoUnit.DAYS);

        // 하루가 지난 Reservation의 ID를 가져옴
        List<Reservation> expiredReservation = reservationRepository.findExpiredReservationsNotInSale(yesterday);

        // 필요한 로직을 처리 (예: Sale 테이블에 추가)
        for (Reservation reservation : expiredReservation) {
            Sales sales = new Sales();
            sales.setReservation(reservation);  // Sales 테이블에 예약 ID 설정
            sales.setSalDate(LocalDate.now());  // 현재 날짜 설정
            sales.setHostInfo(reservation.getRoomInfo().getHostInfo());
            salesRepository.save(sales); // Sales 테이블에 삽입
        }

        System.out.println("매출 테이블 추가");
    }


    public List<SalesCustomDTO> getAllSales(){
        List<SalesCustomDTO> salesCustomDTOs = new ArrayList<>();

        List<Sales> sales = salesRepository.findAll();
        System.out.println(sales);

        for(Sales s: sales){
            HostInfoDTO hostInfoDTO =  HostInfoMapper.INSTANCE.toHostInfoDTO(s.getHostInfo());
            ReservationDTO reservationDTO = ReservationMapper.INSTANCE.toReservationDTO(s.getReservation());
            PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toPaymentDTO(s.getReservation().getPayment());
            SalesDTO salesDTO = SalesMapper.INSTANCE.toSalesDTO(s);
            SalesCustomDTO salesCustomDTO = new SalesCustomDTO(hostInfoDTO, reservationDTO, paymentDTO, salesDTO);
            salesCustomDTOs.add(salesCustomDTO);
        }

        return salesCustomDTOs;
    }

    public List<Top5SalesDTO> getTop5SalesByRegion(String region){
        //List<Top5SalesDTO> top5SalesDTOs = new ArrayList<>();

        List<Top5SalesDTO> top5SalesDTOs = salesRepository.findTop5SalesByRegion(region);

        // for(Sales s: sales){
        //     HostInfoDTO hostInfoDTO =  HostInfoMapper.INSTANCE.toHostInfoDTO(s.getHostInfo());
        //     ReservationDTO reservationDTO = ReservationMapper.INSTANCE.toReservationDTO(s.getReservation());
        //     PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toPaymentDTO(s.getReservation().getPayment());
        //     SalesDTO salesDTO = SalesMapper.INSTANCE.toSalesDTO(s);
        //     Top5SalesDTO top5SalesDTO = new Top5SalesDTO(hostInfoDTO, reservationDTO, paymentDTO, salesDTO);
        //     top5SalesDTOs.add(top5SalesDTO);
        // }

        return top5SalesDTOs;
    }

    public List<SalesCustomDTO> searchSales(String dateRange, String region){
        List<SalesCustomDTO> salesCustomDTOs = new ArrayList<>();

        String startDate = null;
        String endDate = null;

        if(dateRange != null && dateRange.length() > 0){
            String[] dateRangeArr = dateRange.split(" ~ ");
            System.out.println("dateRangeArr: "+dateRangeArr[0]);
            System.out.println("dateRangeArr: "+dateRangeArr[1]);
            startDate = dateRangeArr[0];
            endDate = dateRangeArr[1];
        }
        
        List<Sales> sales = salesRepository.searchSales(startDate, endDate, region);

        for(Sales s: sales){
            HostInfoDTO hostInfoDTO =  HostInfoMapper.INSTANCE.toHostInfoDTO(s.getHostInfo());
            ReservationDTO reservationDTO = ReservationMapper.INSTANCE.toReservationDTO(s.getReservation());
            PaymentDTO paymentDTO = PaymentMapper.INSTANCE.toPaymentDTO(s.getReservation().getPayment());
            SalesDTO salesDTO = SalesMapper.INSTANCE.toSalesDTO(s);
            SalesCustomDTO salesCustomDTO = new SalesCustomDTO(hostInfoDTO, reservationDTO, paymentDTO, salesDTO);
            salesCustomDTOs.add(salesCustomDTO);
        }

        return salesCustomDTOs;

    }
    
    @Transactional(readOnly = true)
    public List<HostSalesDTO> getHostSales(Integer hidx) {
        return salesRepository.findHostSales(hidx);
    }

    @Transactional(readOnly = true)
    public List<HostSalesDTO> searchHostSales(String roomType, String dateValue, Integer hidx) {
        if (dateValue != null && !dateValue.trim().isEmpty()) {
            String[] date = dateValue.split(" ~ ");
            if (date.length < 2 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                throw new IllegalArgumentException("Invalid date range format");
            }
            String startDate = date[0];
            String endDate = date[1];

            return salesRepository.searchHostSales(roomType, startDate, endDate, hidx);

        } else {

            return salesRepository.searchHostSales(roomType, null, null, hidx);
        }
    }

    //호스트 년매출 구하기
    @Transactional(readOnly = true)
    public List<HostSalesDTO> getHostYearSales(Integer hidx, int year) {
        return salesRepository.findHostYearSales(hidx, year);
    }

    //호스트 월매출 구하기
    @Transactional(readOnly = true)
    public List<HostSalesDTO> getHostMonthSales(Integer hidx, int year, int month) {
        return salesRepository.findHostMonthSales(hidx, year, month);
    }

}
