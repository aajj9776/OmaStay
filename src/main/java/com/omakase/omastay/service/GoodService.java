package com.omakase.omastay.service;

import com.google.common.base.Optional;
import com.omakase.omastay.dto.GoodDTO;
import com.omakase.omastay.entity.Good;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.mapper.GoodMapper;
import com.omakase.omastay.repository.GoodRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodService {

    @Autowired
    private GoodRepository goodRepository;
   
    @Transactional
     public Map<String, Object> addGood(GoodDTO goodDTO){
        Integer memIdx = goodDTO.getMemIdx();
        Integer revIdx = goodDTO.getRevIdx();

        Optional<Good> existingGoodOptional = goodRepository.findByMember_IdAndReview_Id(memIdx, revIdx);

        Map<String, Object> result = new HashMap<>(); 

        if (existingGoodOptional.isPresent()) {
            Good existingGood = existingGoodOptional.get();
            existingGood.setGoodStatus(existingGood.getGoodStatus() == BooleanStatus.FALSE ? BooleanStatus.TRUE : BooleanStatus.FALSE); 
            goodRepository.save(existingGood);

            result.put("newGoodIdx", existingGood.getId()); // 또는 다른 필요한 정보
            result.put("goodStatus", existingGood.getGoodStatus()); 
        } else { 
            Good good = GoodMapper.INSTANCE.toGood(goodDTO);
            good.setGoodStatus(BooleanStatus.TRUE);
            good.setGoodDate(LocalDateTime.now());
            Good savedGood = goodRepository.save(good);

            result.put("newGoodIdx", savedGood.getId());
            result.put("goodStatus", savedGood.getGoodStatus()); 
        }
    return result; 
}
}




