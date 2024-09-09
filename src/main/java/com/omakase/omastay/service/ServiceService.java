package com.omakase.omastay.service;


import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;
import com.omakase.omastay.mapper.ServiceMapper;
import com.omakase.omastay.repository.ServiceRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    //  Get all the host notice
    public List<ServiceDTO> getAllHostNotice(){   
        List<com.omakase.omastay.entity.Service> hostNotice = serviceRepository.findBySCateAndSAuth(SCate.NOTICE, UserAuth.HOST, BooleanStatus.TRUE);

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

    //게시판 저장
    public void saveHostNotice(ServiceDTO serviceDTO) {

        serviceDTO.setSCate(SCate.NOTICE);
        serviceDTO.setSAuth(UserAuth.HOST);
        serviceDTO.setSStatus(BooleanStatus.TRUE);
        serviceDTO.setSDate(LocalDateTime.now());

        System.out.println(serviceDTO.getFileName().getFName());
        System.out.println(serviceDTO.getFileName().getOName());

        
        com.omakase.omastay.entity.Service service = ServiceMapper.INSTANCE.toService(serviceDTO);
        System.out.println(service.getFileName().getFName());
        System.out.println(service.getFileName().getOName());


        serviceRepository.save(service);
    }

    public ServiceDTO getHostNotice(int id) {
        com.omakase.omastay.entity.Service service = serviceRepository.findById(id).get();
        return ServiceMapper.INSTANCE.toServiceDTO(service);
    }

    /*
     * all
     * ㅁㄴㅇ
     * 2024-08-08 ~ 2024-09-03
     */
    public List<ServiceDTO> searchHostNotice(String type, String keyword, String date){
        List<com.omakase.omastay.entity.Service> services;
        if(!date.isEmpty()){
            String[] date2 = date.split(" ~ ");
            String startDate = date2[0];
            String endDate = date2[1];

            services = serviceRepository.searchHostNotice(type, keyword, startDate, endDate);
        }else{
            services = serviceRepository.searchHostNotice(type, keyword, null, null);
        }

        
        return ServiceMapper.INSTANCE.toServiceDTOList(services);
    }

    public void modifyHostNotice(ServiceDTO serviceDTO) {
        com.omakase.omastay.entity.Service service = serviceRepository.findById(serviceDTO.getId()).get();
        service.setSTitle(serviceDTO.getSTitle());
        service.setSContent(serviceDTO.getSContent());
        service.setFileName(serviceDTO.getFileName());

        serviceRepository.save(service);
    }

}
