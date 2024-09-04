package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PointDTO;
import com.omakase.omastay.entity.Point;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PointMapper {
    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);

    @Mapping(source = "member.id", target = "memIdx")
    PointDTO toPointDTO(Point point);

    @Mapping(source = "memIdx", target = "member.id")
    Point toPoint(PointDTO pointDTO);

    List<PointDTO> toPointDTOList(List<Point> pointList);

    List<Point> toPointList(List<PointDTO> pointDTOList);
}