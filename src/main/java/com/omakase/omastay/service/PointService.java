package com.omakase.omastay.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Point;
import com.omakase.omastay.mapper.PointMapper;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.repository.PointRepository;
import com.omakase.omastay.repository.ReservationRepository;

import jakarta.transaction.Transactional;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<PointDTO> getAllPoints(){
        List<Point> pList = pointRepository.findAll();

        return PointMapper.INSTANCE.toPointDTOList(pList);
    }

    
    @Transactional
    public int addPoint(String email, PointDTO pDto){
        int cnt=0; 

        //아이디로 member idx 가져오기
        Member member = memberRepository.findByEmail(email);

        //아이디가 없으면 리턴
        if(member == null) return cnt;
        
        pDto.setMemIdx(member.getId()); // 속성 이름이 mIdx인지 확인
        Integer memIdx = pDto.getMemIdx();
        Integer sum = pointRepository.getSumPoint(memIdx);

        if(sum==null){ // 속성 이름이 mIdx인지 확인
            pDto.setPSum(pDto.getPValue());

        }else{
            pDto.setPSum(pDto.getPValue()+sum);
        }
        pDto.setPDate(LocalDateTime.now());
        
        Point point = pointRepository.save(PointMapper.INSTANCE.toPoint(pDto));

        if(point != null) cnt=1;

        return cnt;
    }

    public List<PointDTO> getPoint(int id) {
        List<Point> pList = pointRepository.findByMemIdx(id); // 속성 이름이 mIdx인지 확인

        return PointMapper.INSTANCE.toPointDTOList(pList);
    }

    @Transactional
    public PointDTO savePoint(PointDTO pointDTO) {
        Point res = PointMapper.INSTANCE.toPoint(pointDTO);
        
        List<Integer> sum = pointRepository.findLatestPSumByMemIdx(pointDTO.getMemIdx());
        if( sum != null && sum.size() > 0){
            int sumPoint =  sum.get(0) - pointDTO.getPValue();
            StringBuilder sb = new StringBuilder();
            sb.append("-").append(pointDTO.getPValue());
            res.setPValue(Integer.parseInt(sb.toString()));
            res.setPDate(LocalDateTime.now());
            res.setPSum(sumPoint);
            res.setPContent("포인트 사용");
            Point point = pointRepository.save(res);
            PointDTO dto = PointMapper.INSTANCE.toPointDTO(point);
            return dto;
        }
        return null;
        
    }

    public Integer getSumPoint(int id) {
        List<Integer> sum = pointRepository.findLatestPSumByMemIdx(id);
        return sum.get(0);

    }


    @Transactional
    public PointDTO getCancelPoint(Integer pIdx, Integer memIdx) {
        Point point = pointRepository.findByIdAndMemIdx(pIdx, memIdx);
        int value = Math.abs(point.getPValue());
        int pSum = point.getPSum();
        int sum = value + pSum;
        point.setPSum(sum);
        point.setPValue(value);
        point.setPContent("예약취소");
        Point save = pointRepository.save(point);
        return PointMapper.INSTANCE.toPointDTO(save);
    }


   
}
