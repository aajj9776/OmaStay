package com.omakase.omastay.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.omakase.omastay.dto.MemberDTO;
import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.dto.custom.PointCustomDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Point;
import com.omakase.omastay.mapper.MemberMapper;
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

    public List<PointCustomDTO> getAllPoints(){
        List<PointCustomDTO> list = new ArrayList<>();
        List<Point> pList = pointRepository.findAll();

        for(Point p : pList){
            PointCustomDTO dto = new PointCustomDTO();
            dto.setMember(MemberMapper.INSTANCE.toMemberDTO(p.getMember()));
            dto.setPoint(PointMapper.INSTANCE.toPointDTO(p));
            list.add(dto);
        }

        return list;
    }

    ////관리자 포인트 추가
    @Transactional
    public int addPoint(String email, PointDTO pDto){
        int cnt=0; 

        //아이디로 member idx 가져오기
        Member member = memberRepository.findByEmail(email);

        //아이디가 없으면 리턴
        if(member == null) return cnt;
        
        pDto.setMemIdx(member.getId()); // 속성 이름이 mIdx인지 확인
        Integer memIdx = pDto.getMemIdx();
        Pageable pageable = PageRequest.of(0, 1);
        
        List<Integer> pp = pointRepository.getSumPoint(memIdx, pageable);
        if(!pp.isEmpty()){
            Integer sum = pp.get(0);
            pDto.setPSum(pDto.getPValue()+sum);
        }else{
            pDto.setPSum(pDto.getPValue());
        }

        pDto.setPDate(LocalDateTime.now());
        
        //저장하기
        Point point = pointRepository.save(PointMapper.INSTANCE.toPoint(pDto));

        if(point != null) 
            cnt=1;

        return cnt;
    }

    public List<PointDTO> getPoint(int id) {
        List<Point> pList = pointRepository.findByMemIdx(id); // 속성 이름이 mIdx인지 확인

        return PointMapper.INSTANCE.toPointDTOList(pList);
    }

    @Transactional
    public PointDTO savePoint(PointDTO pointDTO) {
        Point res = PointMapper.INSTANCE.toPoint(pointDTO);
        
        // 가장 최근 포인트 합계를 조회
        List<Integer> sum = pointRepository.findLatestPSumByMemIdx(pointDTO.getMemIdx());
        if (sum != null && sum.size() > 0) {
            int currentSum = sum.get(0);  // 현재 포인트 합계
            
            // 포인트 값이 양수인지 음수인지 상관없이 절대값으로 사용
            int usePoint = Math.abs(pointDTO.getPValue());  
            
            // pValue가 양수라면 차감, 음수라면 더하지 않도록 처리
            if (pointDTO.getPValue() > 0) {
                res.setPValue(-usePoint);  // 음수로 기록
            } else {
                res.setPValue(pointDTO.getPValue());  // 이미 음수라면 그대로 기록
            }

            int sumPoint = currentSum - usePoint;  // 포인트 사용 후 남은 포인트 계산
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
