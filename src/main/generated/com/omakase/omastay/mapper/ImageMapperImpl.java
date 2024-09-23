package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ImageDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Image;
import com.omakase.omastay.entity.RoomInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T16:50:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageDTO toImageDTO(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();

        imageDTO.setRId( imageRoomInfoId( image ) );
        imageDTO.setHIdx( imageHostInfoId( image ) );
        imageDTO.setId( image.getId() );
        imageDTO.setImgCate( image.getImgCate() );
        imageDTO.setImgName( image.getImgName() );
        imageDTO.setImgStatus( image.getImgStatus() );
        imageDTO.setImgNone( image.getImgNone() );

        return imageDTO;
    }

    @Override
    public Image toImage(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        Image image = new Image();

        image.setRoomInfo( imageDTOToRoomInfo( imageDTO ) );
        image.setHostInfo( imageDTOToHostInfo( imageDTO ) );
        image.setId( imageDTO.getId() );
        image.setImgCate( imageDTO.getImgCate() );
        image.setImgName( imageDTO.getImgName() );
        image.setImgStatus( imageDTO.getImgStatus() );
        image.setImgNone( imageDTO.getImgNone() );

        return image;
    }

    @Override
    public List<ImageDTO> toImageDTOList(List<Image> imageList) {
        if ( imageList == null ) {
            return null;
        }

        List<ImageDTO> list = new ArrayList<ImageDTO>( imageList.size() );
        for ( Image image : imageList ) {
            list.add( toImageDTO( image ) );
        }

        return list;
    }

    @Override
    public List<Image> toImageList(List<ImageDTO> imageDTOList) {
        if ( imageDTOList == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( imageDTOList.size() );
        for ( ImageDTO imageDTO : imageDTOList ) {
            list.add( toImage( imageDTO ) );
        }

        return list;
    }

    private Integer imageRoomInfoId(Image image) {
        if ( image == null ) {
            return null;
        }
        RoomInfo roomInfo = image.getRoomInfo();
        if ( roomInfo == null ) {
            return null;
        }
        Integer id = roomInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer imageHostInfoId(Image image) {
        if ( image == null ) {
            return null;
        }
        HostInfo hostInfo = image.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected RoomInfo imageDTOToRoomInfo(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        RoomInfo roomInfo = new RoomInfo();

        roomInfo.setId( imageDTO.getRId() );

        return roomInfo;
    }

    protected HostInfo imageDTOToHostInfo(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( imageDTO.getHIdx() );

        return hostInfo;
    }
}
