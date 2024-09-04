package com.omakase.omastay.service;


import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.mapper.ServiceMapper;
import com.omakase.omastay.repository.ServiceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    //  Get all the host notice
    public List<ServiceDTO> getAllHostNotice(){   
        List<com.omakase.omastay.entity.Service> hostNotice = serviceRepository.findBySCateAndSAuth(SCate.NOTICE, UserAuth.HOST);

        return ServiceMapper.INSTANCE.toServiceDTOList(hostNotice);
    }
    
    public int deleteHostNotice(List<Integer> ids) {
        int[] idArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idArray[i] = ids.get(i);
        }

        int cnt = serviceRepository.deleteById(idArray);
        return cnt;
    }


}
