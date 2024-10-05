package com.omakase.omastay.service;

import com.omakase.omastay.dto.CalculationDTO;
import com.omakase.omastay.dto.custom.HostCalculationDTO;
import com.omakase.omastay.dto.custom.HostSalesDTO;
import com.omakase.omastay.entity.enumurate.CalStatus;
import com.omakase.omastay.mapper.CalculationMapper;
import com.omakase.omastay.repository.CalculationRepository;
import com.omakase.omastay.repository.SalesRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


}
