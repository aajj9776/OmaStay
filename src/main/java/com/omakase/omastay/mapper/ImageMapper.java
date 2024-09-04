package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.entity.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    @Mapping(source = "roomInfo.id", target = "RId")
    @Mapping(source = "hostInfo.id", target = "HIdx")
    ImageDTO toImageDTO(Image image);

    @Mapping(source = "RId", target = "roomInfo.id")
    @Mapping(source = "HIdx", target = "hostInfo.id")
    Image toImage(ImageDTO imageDTO);

    List<ImageDTO> toImageDTOList(List<Image> imageList);

    List<Image> toImageList(List<ImageDTO> imageDTOList);
}