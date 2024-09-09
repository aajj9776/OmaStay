package com.omakase.omastay.repository.custom;

import java.util.List;

import com.omakase.omastay.entity.Service;

public interface ServiceRepositoryCustom {
    
    List<Service> searchHostNotice(String type, String keyword, String startDate, String endDate);
}
