package com.omakase.omastay.service;

import com.google.api.client.util.Value;
import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.Recommendation;
import com.omakase.omastay.entity.enumurate.ImgCate;
import com.omakase.omastay.mapper.ImageMapper;
import com.omakase.omastay.repository.ImageRepository;
import com.omakase.omastay.repository.RecommendationRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Autowired
    private ImageRepository imageRepository;


    public List<Recommendation> getRecommHost(){
        return recommendationRepository.findAllWithSort();
    }

    public ImageDTO getImage(Integer hIdx) {
        System.out.println(ImgCate.HOST);
        Image image = imageRepository.findByHostInfoAndImgCate(hIdx, ImgCate.HOST).get(0);
        System.out.println("<추천숙소 이미지> " + image);
    return ImageMapper.INSTANCE.toImageDTO(image);
    }

    public List<ImageDTO> getAllHostImage(Integer hIdx) {
        List<Image> image = imageRepository.findByHostInfoAndImage(hIdx, ImgCate.HOST);
    return ImageMapper.INSTANCE.toImageDTOList(image);
    }

    public List<ImageDTO> getAllRoomImage(Integer hIdx) {
        List<Image> image = imageRepository.findByRoomInfoAndImage(hIdx, ImgCate.ROOM);
    return ImageMapper.INSTANCE.toImageDTOList(image);
    }



    



}
