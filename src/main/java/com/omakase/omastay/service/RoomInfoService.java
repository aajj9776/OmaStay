package com.omakase.omastay.service;


import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.RoomRegDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.HStep;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.entity.enumurate.RoomStatus;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.RoomInfoMapper;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.ImageRepository;
import com.omakase.omastay.repository.PriceRepository;
import com.omakase.omastay.repository.RoomInfoRepository;
import com.omakase.omastay.vo.PeakVo;
import com.omakase.omastay.vo.SemiPeakVo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomInfoService {

    @Autowired
    private RoomInfoRepository roomInfoRepository;
    
    @Autowired
    private HostInfoRepository hostInfoRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private ImageRepository imageRepository;

    public void saveRoomInfo(HostInfoDTO hostInfoDTO, RoomRegDTO roomRegDTO) {
        
        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);
        RoomInfo roomInfo;
        if(roomRegDTO.getRoomInfo().getId() != null) {
            int rIdx = roomRegDTO.getRoomInfo().getId();
            roomInfo = roomInfoRepository.findById(rIdx);
        } else{
            roomInfo = new RoomInfo();
        }
        roomInfo.setRoomName(roomRegDTO.getRoomInfo().getRoomName());
        roomInfo.setRoomType(roomRegDTO.getRoomInfo().getRoomType());
        roomInfo.setRoomIntro(roomRegDTO.getRoomInfo().getRoomIntro());
        roomInfo.setRoomPerson(roomRegDTO.getRoomInfo().getRoomPerson());
        roomInfo.setHostInfo(hostInfo);
        roomInfo.setRoomStatus(BooleanStatus.TRUE);
        roomInfoRepository.save(roomInfo);
        
        List<Price> priceset = priceRepository.findAllByHostInfoId(hostInfo.getId());
        Price price = priceset.get(0);
        if(price.getRoomInfo() == null) {
            // PeakVo 객체를 가져와서 필요한 값을 설정
            PeakVo peakVo = price.getPeakVo();
            peakVo.setPeakPrice(roomRegDTO.getPrice().getPeakVo().getPeakPrice());
            price.setPeakVo(peakVo);

            // Semi 객체를 가져와서 필요한 값을 설정
            SemiPeakVo semi = price.getSemi();
            semi.setSemiPrice(roomRegDTO.getPrice().getSemi().getSemiPrice());
            price.setSemi(semi);

            price.setRoomInfo(roomInfo);
            price.setRegularPrice(roomRegDTO.getPrice().getRegularPrice());
            priceRepository.save(price);

        } else {

            Price newPrice = new Price();

            newPrice.setHostInfo(price.getHostInfo());
            newPrice.setPeakSet(price.getPeakSet());
            
            // PeakVo 객체를 가져와서 필요한 값을 설정
            PeakVo peakVo = newPrice.getPeakVo();
            peakVo.setPeakStart(price.getPeakVo().getPeakStart());
            peakVo.setPeakEnd(price.getPeakVo().getPeakEnd());
            peakVo.setPeakPrice(roomRegDTO.getPrice().getPeakVo().getPeakPrice());
            newPrice.setPeakVo(peakVo);

            // Semi 객체를 가져와서 필요한 값을 설정
            SemiPeakVo semi = newPrice.getSemi();
            semi.setSemiStart(price.getSemi().getSemiStart());
            semi.setSemiEnd(price.getSemi().getSemiEnd());
            semi.setSemiPrice(roomRegDTO.getPrice().getSemi().getSemiPrice());
            newPrice.setSemi(semi);

            newPrice.setRoomInfo(roomInfo);
            newPrice.setRegularPrice(roomRegDTO.getPrice().getRegularPrice());
            priceRepository.save(newPrice);
        }
        
        for (ImageDTO imageDTO : roomRegDTO.getImages()) {
                Image newImage = new Image();
                newImage.setRoomInfo(roomInfo);
                newImage.setHostInfo(hostInfo);
                newImage.setImgName(imageDTO.getImgName());
                newImage.setImgCate(ImgCate.ROOM);
                newImage.setImgStatus(BooleanStatus.TRUE);
                imageRepository.save(newImage);
        }

        if(hostInfo.getHStep() == HStep.RULE) {
            hostInfo.setHStep(HStep.ROOM); // hStep을 3로 설정
        }

        hostInfoRepository.save(hostInfo);
    }

     // 룸 전체 리스트
    public List<RoomInfoDTO> getAllRoom(HostInfoDTO hostInfoDTO,BooleanStatus roomStatus) {
        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);
        List<RoomInfo> roomlist = roomInfoRepository.findByHostInfoAndRoomStatus(hostInfo,roomStatus);
        return RoomInfoMapper.INSTANCE.toRoomInfoDTOList(roomlist);
    }
    
    //  검색
    public List<RoomInfoDTO> searchRoom(String type, String keyword, HostInfoDTO hostInfoDTO){

        System.out.println("룸인포서비스 서치룸 왔다.");

        HostInfo hostInfo = HostInfoMapper.INSTANCE.toHostInfo(hostInfoDTO);

        List<RoomInfo> roomlist = roomInfoRepository.searchRoom(type, keyword, hostInfo);

        System.out.println("컨트롤러리스트:"+roomlist);

        return RoomInfoMapper.INSTANCE.toRoomInfoDTOList(roomlist);
    }

    // 룸 삭제하기
    public int deleteRoom(List<Integer> ids) {
        int[] idArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idArray[i] = ids.get(i);
        }

        int cnt = roomInfoRepository.deleteById(idArray);
        return cnt;
    }

    public RoomInfoDTO getRoom(int id) {
        RoomInfo roomInfo = roomInfoRepository.findById(id);
        return RoomInfoMapper.INSTANCE.toRoomInfoDTO(roomInfo);
    }
}
