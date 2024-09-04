package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.InquiryDTO;
import com.omakase.omastay.entity.Inquiry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InquiryMapper {
    InquiryMapper INSTANCE = Mappers.getMapper(InquiryMapper.class);

    @Mapping(source = "member.id", target = "MIdx")
    InquiryDTO toInquiryDTO(Inquiry inquiry);

    @Mapping(source = "MIdx", target = "member.id")
    Inquiry toInquiry(InquiryDTO inquiryDTO);

    List<InquiryDTO> toInquiryDTOList(List<Inquiry> inquiryList);

    List<Inquiry> toInquiryList(List<InquiryDTO> inquiryDTOList);
}