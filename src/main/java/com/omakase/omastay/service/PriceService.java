package com.omakase.omastay.service;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.PriceMapper;
import com.omakase.omastay.repository.PriceRepository;
import com.omakase.omastay.vo.PeakVo;
import com.omakase.omastay.vo.SemiPeakVo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.text.DecimalFormat;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceDTO findPriceDTO(HostInfoDTO hostInfoDTO) {

        Price price = priceRepository.findFirstByHostInfoId(hostInfoDTO.getId());

        return PriceMapper.INSTANCE.toPriceDTO(price);
    }

    public void setpeak(HostInfoDTO hostInfoDTO, PriceDTO priceDTO) {

        Price price = priceRepository.findFirstByHostInfoId(hostInfoDTO.getId());

        if(price == null) {
            price = new Price();
            price.setHostInfo(HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO));
        }

        price.setPeakSet(priceDTO.getPeakSet());
        price.setPeakVo(new PeakVo(priceDTO.getPeakVo().getPeakStart(), priceDTO.getPeakVo().getPeakEnd(), null));
        price.setSemi(new SemiPeakVo(priceDTO.getSemi().getSemiStart(), priceDTO.getSemi().getSemiEnd(), null));

        price.setRoomInfo(null);

        priceRepository.save(price);
    }

    // 관리자 - 입점 요청 상회 조회 모달 가격 가져오기
    public PriceDTO getPrice(Integer id) { 
        Price priceDTO = priceRepository.findFirstByRoomInfoId(id);

        return PriceMapper.INSTANCE.toPriceDTO(priceDTO);
    }

    public List<Map<String, Object>> getHostPriceList() {
        List<Price> hostPriceList = priceRepository.findAll();
        List<Map<String, Object>> allMatchedPrice = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
    
        for (Price price : hostPriceList) {
            Map<String, Object> priceData = new HashMap<>();
            priceData.put("hIdx", price.getHostInfo().getId());
    
            // 가격 계산 로직
            Integer matchedPrice = null;
            LocalDateTime peakStart = price.getPeakVo() != null ? price.getPeakVo().getPeakStart() : null;
            LocalDateTime peakEnd = price.getPeakVo() != null ? price.getPeakVo().getPeakEnd() : null;
            LocalDateTime semiStart = price.getSemi() != null ? price.getSemi().getSemiStart() : null;
            LocalDateTime semiEnd = price.getSemi() != null ? price.getSemi().getSemiEnd() : null;
    
            if (peakStart != null && peakEnd != null &&
                (today.isAfter(peakStart) || today.isEqual(peakStart)) &&
                (today.isBefore(peakEnd) || today.isEqual(peakEnd))) {
                matchedPrice = price.getPeakVo().getPeakPrice();
            } else if (semiStart != null && semiEnd != null &&
                       (today.isAfter(semiStart) || today.isEqual(semiStart)) &&
                       (today.isBefore(semiEnd) || today.isEqual(semiEnd))) {
                matchedPrice = price.getSemi().getSemiPrice();
            } else {
                matchedPrice = price.getRegularPrice();
            }
    
            priceData.put("matchedPrice", matchedPrice);
            allMatchedPrice.add(priceData);
        }
    
        return allMatchedPrice;
    }


    public String calculateAveragePrice(Integer id, Integer roomIdx, LocalDate checkIn, LocalDate checkOut) {
        List<Price> prices = priceRepository.findPricesForRoom(id, roomIdx, checkIn, checkOut);

            // 숙박 총 일수 계산 (체크인과 체크아웃 간의 차이)
            long totalDays = ChronoUnit.DAYS.between(checkIn, checkOut);
            if (totalDays == 0) {
                totalDays = 1;  // 같은 날 체크인/체크아웃하는 경우 최소 1박 처리
            }

            double totalPrice = 0;  // 총 가격

        for (Price price : prices) {
            long peakDays = 0;
            long semiDays = 0;
            long regularDays = 0;
    
           // 1. 성수기(peak)가 존재하는 경우
                if (price.getPeakVo() != null) {
                    LocalDate peakStartDate = price.getPeakVo().getPeakStart().toLocalDate();  // LocalDateTime → LocalDate 변환
                    LocalDate peakEndDate = price.getPeakVo().getPeakEnd().toLocalDate();      // LocalDateTime → LocalDate 변환
                    peakDays = getDaysInRange(checkIn, checkOut, peakStartDate, peakEndDate);
                    System.out.println("성수기 시작일: " + peakStartDate);
                    System.out.println("성수기 종료일: " + peakEndDate);
                } else {
                    // peak가 없으면 그 일수는 비성수기로 처리
                    peakDays = 0;
                }

                // 2. 준성수기(semi)가 존재하는 경우
                if (price.getSemi() != null) {
                    LocalDate semiStartDate = price.getSemi().getSemiStart().toLocalDate();  // LocalDateTime → LocalDate 변환
                    LocalDate semiEndDate = price.getSemi().getSemiEnd().toLocalDate();      // LocalDateTime → LocalDate 변환
                    semiDays = getDaysInRange(checkIn, checkOut, semiStartDate, semiEndDate);
                
                } else {
                    // semi가 없으면 그 일수는 비성수기로 처리
                    semiDays = 0;
                }
                    
            // 3. 비성수기(regularDays) 계산: 전체 일수에서 성수기와 준성수기 일수를 제외한 나머지
            regularDays = totalDays - peakDays - semiDays;
    
            // 각 기간의 가격 계산
            double peakPrice = peakDays * (price.getPeakVo() != null ? price.getPeakVo().getPeakPrice() : 0);  // peak가 없으면 0
            double semiPrice = semiDays * (price.getSemi() != null ? price.getSemi().getSemiPrice() : 0);  // semi가 없으면 0
            double regularPrice = regularDays * price.getRegularPrice();  // 비성수기 가격
    
            // 총 가격 계산
            totalPrice += peakPrice + semiPrice + regularPrice;


            System.out.println("성수기 일수: " + peakDays);
            System.out.println("성수기 가격: " + peakPrice);
            System.out.println("왜저래"+ totalPrice +"/"+ peakPrice +"/"+ semiPrice +"/"+ regularPrice);
        }

        double averagePrice = totalPrice / totalDays;

        // 정수로 변환 후 콤마 형식으로 포맷팅
        DecimalFormat decimalFormat = new DecimalFormat("#,###");  // 3자리마다 콤마
        return decimalFormat.format(averagePrice);  // 포맷 적용된 값 반환
    }
    

        private long getDaysInRange(LocalDate checkIn, LocalDate checkOut, LocalDate rangeStart, LocalDate rangeEnd) {
            if (rangeStart == null || rangeEnd == null) {
                return 0; 
            }
        
            LocalDate effectiveStart = (rangeStart.isBefore(checkIn)) ? checkIn : rangeStart;
            LocalDate effectiveEnd = (rangeEnd.isAfter(checkOut)) ? checkOut : rangeEnd;
        
            if (effectiveStart.isAfter(effectiveEnd)) {
                return 0;
            }
        
            return ChronoUnit.DAYS.between(effectiveStart, effectiveEnd);  // 양 끝 포함 계산
        }

    
    }


    
