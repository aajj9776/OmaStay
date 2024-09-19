package com.omakase.omastay.service;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.enumurate.HStep;
import com.omakase.omastay.mapper.AdminMemberMapper;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.PriceMapper;
import com.omakase.omastay.repository.PriceRepository;
import com.omakase.omastay.vo.PeakVo;
import com.omakase.omastay.vo.SemiPeakVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

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
        price.setPeakVo(new PeakVo(priceDTO.getPeakVo().getPeakStart(), priceDTO.getPeakVo().getPeakEnd(), 0));
        price.setSemi(new SemiPeakVo(priceDTO.getSemi().getSemiStart(), priceDTO.getSemi().getSemiEnd(), 0));

        price.setRoomInfo(null);

        priceRepository.save(price);
    }

}
