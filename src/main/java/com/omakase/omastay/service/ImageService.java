package com.omakase.omastay.service;

import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.mapper.ImageMapper;
import com.omakase.omastay.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public List<ImageDTO> getImages(Integer roomId) {
        List<Image> images = imageRepository.findByRoomInfoId(roomId);

        return ImageMapper.INSTANCE.toImageDTOList(images);
    }

    public List<ImageDTO> getHostImages(Integer hostId) {
        List<Image> images = imageRepository.findByHostInfoIdAndImgCate(hostId);
        return ImageMapper.INSTANCE.toImageDTOList(images);
    }
}
