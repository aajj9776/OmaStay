package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.GoodDTO;
import com.omakase.omastay.entity.Good;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface GoodMapper {
    GoodMapper INSTANCE = Mappers.getMapper(GoodMapper.class);

    @Mapping(source = "review.id", target = "revIdx")
    @Mapping(source = "member.id", target = "memIdx")
    GoodDTO toGoodDTO(Good good);

    @Mapping(source = "revIdx", target = "review.id")
    @Mapping(source = "memIdx", target = "member.id")
    Good toGood(GoodDTO goodDTO);

    List<GoodDTO> toGoodDTOList(List<Good> goodList);

    List<Good> toGoodList(List<GoodDTO> goodDTOList);
}
