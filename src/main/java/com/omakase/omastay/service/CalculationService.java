package com.omakase.omastay.service;

import com.omakase.omastay.dto.CalculationDTO;
import com.omakase.omastay.dto.custom.AdminMainCustomDTO;
import com.omakase.omastay.dto.custom.CalculationCustomDTO;
import com.omakase.omastay.dto.custom.HostCalculationDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.entity.Calculation;
import com.omakase.omastay.entity.enumurate.CalStatus;
import com.omakase.omastay.mapper.CalculationMapper;
import com.omakase.omastay.repository.CalculationRepository;
import com.omakase.omastay.repository.SalesRepository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private SalesRepository salesRepository;

    //호스트 년도별 정산 리스트 만들기
    @Transactional(readOnly = true)
    public List<HostCalculationDTO> getAllHostCal(Integer hidx, Integer year) {

        List<HostCalculationDTO> finalHostCal = new ArrayList<HostCalculationDTO>();

        for(int i=1; i<=12; i++) {
            List<HostSalesDTO> hostsal = salesRepository.findHostMonthSales(hidx, year, i);

            if(hostsal.size() > 0){
                HostCalculationDTO hostCal = new HostCalculationDTO();
                hostCal.setYear(year);
                hostCal.setMonth(i);
                hostCal.setSalCount(hostsal.size());
                CalculationDTO cal = calculationRepository.findHostAndMonth(hidx, i);
                if(cal == null) {
                    hostCal.setCalStatus(null);
                } else {
                    hostCal.setCalStatus(cal.getCalStatus());
                }
                Integer salePriceSum = 0;
                for(int j=0; j<hostsal.size(); j++) {
                    salePriceSum += Integer.parseInt(hostsal.get(j).getNsalePrice());
                }
                hostCal.setSalAmount(salePriceSum);
                Integer commission = (int)(salePriceSum*0.1);
                hostCal.setCommission(commission);
                Integer calAmount = (int)(salePriceSum*0.9);
                hostCal.setCalAmount(calAmount);
                finalHostCal.add(hostCal);
            }
        }
        
        return finalHostCal;
    }

    public int insertCal(HostCalculationDTO item, Integer hIdx){
        CalculationDTO cal = calculationRepository.findHostAndMonth(hIdx, item.getMonth());
        if(cal == null) {
        cal = new CalculationDTO();
        cal.setHIdx(hIdx);
        cal.setCalAmount(item.getCalAmount());
        LocalDateTime calMonth = LocalDateTime.of(item.getYear(), item.getMonth(), 1, 0, 0, 0);
        cal.setCalMonth(calMonth);
        cal.setCalStatus(CalStatus.REQUEST);
        cal.setCalRegTime(LocalDateTime.now());
        calculationRepository.save(CalculationMapper.INSTANCE.toCalculation(cal));
        return 1;
        } else {
            return 0;
        }
    }

    //호스트 월별 정산 리스트 
    @Transactional(readOnly = true)
    public List<HostSalesDTO> getMonthHostCal(Integer hidx, Integer year, Integer month) {

        List<HostSalesDTO> hostsal = salesRepository.findHostMonthSales(hidx, year, month);

        return hostsal;
    }


    // 정산 리스트 가져오기
    public List<CalculationCustomDTO> getCalculationMonthly(String period){

        List<CalculationCustomDTO> calList = new ArrayList<CalculationCustomDTO>();

        if(period == null){ //검색된 기간이 없을 경우 -> 정산 요청한 월을 기준으로 보여줌

            //정산할 매출의 달 (ex. 정산요청 10월 -> 매출달 9월)
            LocalDateTime firstDateTime = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth().minus(1), 1, 0, 0);
            
            //정산 기준 월으로 정산 리스트 가져오기
            List<Calculation> calculation = calculationRepository.calculationMonthly(firstDateTime);

            for(Calculation cal : calculation){ 
                //엔티티 -> DTO로 변환
                CalculationCustomDTO calDTO = new CalculationCustomDTO();
                calDTO.setCalculationDTO(CalculationMapper.INSTANCE.toCalculationDTO(cal));
                calDTO.setHIdx(cal.getHostInfo().getId()); 
                calDTO.setHostName(cal.getHostInfo().getHname());
                calDTO.setRequestSum(cal.getCalAmount());
    
                //요청된 정산금이 맞는지 검증, 지난달 매출을 기준으로 검증
                LocalDate lastMonthStart = LocalDate.of(firstDateTime.getYear(), firstDateTime.getMonth(), 1);  
                LocalDate lastMonthEnd = LocalDate.of(firstDateTime.getYear(), firstDateTime.getMonth(), firstDateTime.getMonth().maxLength());

                CalculationCustomDTO temp = salesRepository.findHostMonthPayment(cal.getHostInfo().getId(), lastMonthStart, lastMonthEnd); //cost, sales, commission, calAmount 계산, 검증
                
                calDTO.setCost(temp.getCost()); //매출 원가
                calDTO.setSell(temp.getSales()); //할인액
                calDTO.setSales(temp.getCost() - temp.getSales()); //할인액 (본사 부담 할인)
                calDTO.setCommission((int) (temp.getCost() * 0.1)); //수수료
                calDTO.setCalAmount(calDTO.getCost() - calDTO.getCommission()); //정산금 (매출 원가 - 수수료)

                if(calDTO.getRequestSum().equals(calDTO.getCalAmount())){
                    System.out.println("정산금액이 일치합니다.");
                } else {
                    System.out.println("정산금액이 일치하지 않습니다.");
                    calDTO.setCost(0); 
                    calDTO.setSales(0); 
                    calDTO.setCommission(0); 
                    calDTO.setCalAmount(0); 
                }
                calList.add(calDTO);
            }
        }
        
        return calList;
    }

    // 정산 승인
    @Transactional
    public void approveCalculation(Integer id){
        calculationRepository.approveCalculation(id);
    }

    // 정산 완료
    @Transactional
    public void completeCalculation(Integer id){
        calculationRepository.completeCalculation(id);
    }

    public CalculationDTO getCal(Integer cIdx){
        Calculation cal = calculationRepository.findOneById(cIdx);
        CalculationDTO calDTO = CalculationMapper.INSTANCE.toCalculationDTO(cal);
        //calDTO.setHname(cal.getHostInfo().getHname());
        return calDTO;
    }

    public List<AdminMainCustomDTO> getCalculationCount(){
        LocalDateTime thisMonth = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withMinute(0);
        List<AdminMainCustomDTO> list = calculationRepository.getCalculationCount(thisMonth);
        System.out.println(list);
        return list;
    }
}
