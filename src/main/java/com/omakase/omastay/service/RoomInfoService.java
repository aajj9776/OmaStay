package com.omakase.omastay.service;


import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.dto.RoomInfoDTO;
import com.omakase.omastay.dto.custom.RoomRegDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.RoomInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.HStep;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.mapper.ImageMapper;
import com.omakase.omastay.mapper.RoomInfoMapper;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.ImageRepository;
import com.omakase.omastay.repository.PriceRepository;
import com.omakase.omastay.repository.RoomInfoRepository;
import com.omakase.omastay.vo.PeakVo;
import com.omakase.omastay.vo.SemiPeakVo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
            PeakVo peakVo = Optional.ofNullable(price.getPeakVo()).orElse(new PeakVo());
            Integer peakPrice = Optional.ofNullable(roomRegDTO.getPrice())
                                    .map(PriceDTO::getPeakVo)
                                    .map(PeakVo::getPeakPrice)
                                    .orElse(null);
            peakVo.setPeakPrice(peakPrice);
            price.setPeakVo(peakVo);

            // Semi 객체를 가져와서 필요한 값을 설정
            SemiPeakVo semi = Optional.ofNullable(price.getSemi()).orElse(new SemiPeakVo());
            Integer semiPrice = Optional.ofNullable(roomRegDTO.getPrice())
                            .map(PriceDTO::getSemi)
                            .map(SemiPeakVo::getSemiPrice)
                            .orElse(null);
            semi.setSemiPrice(semiPrice);
            price.setSemi(semi);

            price.setRoomInfo(roomInfo);
            price.setRegularPrice(roomRegDTO.getPrice().getRegularPrice());
            priceRepository.save(price);

        } else {

            Price existPrice = priceRepository.findByRoomInfoId(roomInfo.getId());
            Price savePrice;
            if (existPrice != null) {
                savePrice = existPrice;
            } else {
                // 새로운 Price 객체 생성
                savePrice = new Price();
                savePrice.setHostInfo(price.getHostInfo());
                savePrice.setPeakSet(price.getPeakSet());
                savePrice.setRoomInfo(roomInfo);
            }
            
            // PeakVo 객체를 가져와서 필요한 값을 설정
            PeakVo peakVo = Optional.ofNullable(savePrice.getPeakVo()).orElse(new PeakVo());
            peakVo.setPeakStart(Optional.ofNullable(price.getPeakVo())
                                        .map(PeakVo::getPeakStart)
                                        .orElse(null));
            peakVo.setPeakEnd(Optional.ofNullable(price.getPeakVo())
                                    .map(PeakVo::getPeakEnd)
                                    .orElse(null));
            Integer peakPrice = Optional.ofNullable(roomRegDTO.getPrice())
                                        .map(PriceDTO::getPeakVo)
                                        .map(PeakVo::getPeakPrice)
                                        .orElse(null);
            peakVo.setPeakPrice(peakPrice);
            savePrice.setPeakVo(peakVo);

            // Semi 객체를 가져와서 필요한 값을 설정
            SemiPeakVo semi = Optional.ofNullable(savePrice.getSemi()).orElse(new SemiPeakVo());
            semi.setSemiStart(Optional.ofNullable(price.getSemi())
                                    .map(SemiPeakVo::getSemiStart)
                                    .orElse(null));
            semi.setSemiEnd(Optional.ofNullable(price.getSemi())
                                    .map(SemiPeakVo::getSemiEnd)
                                    .orElse(null));
            Integer semiPrice = Optional.ofNullable(roomRegDTO.getPrice())
                                        .map(PriceDTO::getSemi)
                                        .map(SemiPeakVo::getSemiPrice)
                                        .orElse(null);
            semi.setSemiPrice(semiPrice);
            savePrice.setSemi(semi);

            
            savePrice.setRegularPrice(roomRegDTO.getPrice().getRegularPrice());
            priceRepository.save(savePrice);
        }

        List<Image> existingImages = imageRepository.findByRoomInfoId(roomInfo.getId());
        List<ImageDTO> newImages = roomRegDTO.getImages();
        System.out.println("roomRegDTO.getImages()"+newImages.size());
        // 기존 이미지 상태 변경
        for (Image existingImage : existingImages) {
            boolean isImageInNewList = newImages.stream()
                .anyMatch(newImage -> newImage.getImgName().getFName().equals(existingImage.getImgName().getFName()));
            if (!isImageInNewList) {
                existingImage.setImgStatus(BooleanStatus.FALSE);
                imageRepository.save(existingImage);
            }
        }

        // 새로운 이미지 추가
        for (ImageDTO imageDTO : roomRegDTO.getImages()) {
            boolean isExistingImage = existingImages.stream()
                 .anyMatch(existingImage -> existingImage.getImgName().getFName().equals(imageDTO.getImgName().getFName()));
            if (!isExistingImage) {
                Image newImage = new Image();
                newImage.setRoomInfo(roomInfo);
                newImage.setHostInfo(hostInfo);
                newImage.setImgName(imageDTO.getImgName());
                newImage.setImgCate(ImgCate.ROOM);
                newImage.setImgStatus(BooleanStatus.TRUE);
                imageRepository.save(newImage);
            }
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

    public RoomInfoDTO getRoomInfo(Integer roomIdx) {
        RoomInfo res = roomInfoRepository.findById(roomIdx).get();
        return RoomInfoMapper.INSTANCE.toRoomInfoDTO(res);
    }

    public ImageDTO getImage(Integer hIdx) {
        System.out.println(ImgCate.HOST);
        Image image = imageRepository.findByHostInfoAndImgCate(hIdx, ImgCate.HOST).get(0);
        System.out.println("서비스 이미지" + image);
        return ImageMapper.INSTANCE.toImageDTO(image);
    }

    public HostInfoDTO getHostInfo(Integer hIdx) {
        return HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfoRepository.findById(hIdx).get());
    }

    public List<RoomInfoDTO> getAvailableRooms(Integer hIdx, LocalDate startDate, LocalDate endDate, Integer person){
            List<RoomInfo> roomInfoList = roomInfoRepository.findAvailableRooms(hIdx, startDate, endDate, person);
            return RoomInfoMapper.INSTANCE.toRoomInfoDTOList(roomInfoList);
    }

    public List<RoomInfoDTO> getAllRoom(Integer hIdx){
        List<RoomInfo> allRoomInfo = roomInfoRepository.findAllRommHidx(hIdx);
        return RoomInfoMapper.INSTANCE.toRoomInfoDTOList(allRoomInfo);
    }

    
}

