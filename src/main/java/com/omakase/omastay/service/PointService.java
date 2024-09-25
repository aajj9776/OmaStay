package com.omakase.omastay.service;

import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.entity.Member;
import com.omakase.omastay.entity.Point;
import com.omakase.omastay.mapper.PointMapper;
import com.omakase.omastay.repository.MemberRepository;
import com.omakase.omastay.repository.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PointService {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<PointDTO> getAllPoints(){
        List<Point> pList = pointRepository.findAll();

        return PointMapper.INSTANCE.toPointDTOList(pList);
    }

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

    public PointDTO savePoint(PointDTO pointDTO) {
        Point res = PointMapper.INSTANCE.toPoint(pointDTO);
        
        Integer sum = pointRepository.findLatestPSumByMemIdx(pointDTO.getMemIdx());
        System.out.println("sum : " + sum);
        
        res.setPDate(LocalDateTime.now());
        res.setPSum(null);
        Point point = pointRepository.save(res);
        PointDTO dto = PointMapper.INSTANCE.toPointDTO(point);
        return dto;
    }

   
}
