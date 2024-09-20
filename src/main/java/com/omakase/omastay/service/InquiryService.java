package com.omakase.omastay.service;

import com.omakase.omastay.dto.InquiryDTO;
import com.omakase.omastay.entity.Inquiry;
import com.omakase.omastay.mapper.InquiryMapper;
import com.omakase.omastay.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;


    public List<InquiryDTO> getAllInquiries(){
        List<Inquiry> inquiries = inquiryRepository.getAllInquiries();

        return InquiryMapper.INSTANCE.toInquiryDTOList(inquiries);
    }

}
