package com.omakase.omastay.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
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
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    // 전체 게시글 가져오기
    public List<ServiceDTO> getAllServices(SCate sCate, UserAuth sAuth) {
        List<com.omakase.omastay.entity.Service> services = serviceRepository.findBySCateAndSAuth(sCate, sAuth, BooleanStatus.TRUE);
        return ServiceMapper.INSTANCE.toServiceDTOList(services);
    }
    
    // 호스트 공지사항 게시글 삭제하기
    public int deleteService(List<Integer> ids) {
        int[] idArray = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            idArray[i] = ids.get(i);
        }

        int cnt = serviceRepository.deleteById(idArray);
        return cnt;
    }

    // 게시글 저장
    public void saveService(ServiceDTO serviceDTO) {
        serviceDTO.setSStatus(BooleanStatus.TRUE);
        serviceDTO.setSDate(LocalDateTime.now());

        com.omakase.omastay.entity.Service service = ServiceMapper.INSTANCE.toService(serviceDTO);

        serviceRepository.save(service);
    }

    // 게시글 id 값으로 serviceDTO 가져오기
    public ServiceDTO getServices(int id) {
        com.omakase.omastay.entity.Service service = serviceRepository.findById(id).get();
        return ServiceMapper.INSTANCE.toServiceDTO(service);
    }

    // 게시글 수정
    @Transactional
    public void modifyServices(ServiceDTO serviceDTO, int key) {
        com.omakase.omastay.entity.Service service = serviceRepository.findById(serviceDTO.getId()).get();
        service.setSTitle(serviceDTO.getSTitle());
        service.setSContent(serviceDTO.getSContent());
        service.setSCate(serviceDTO.getSCate());

        
        if(key==0){ // 새 파일이 존재하는 경우
            service.setFileName(serviceDTO.getFileName());
            serviceRepository.modifyServices(service);
        }else if(key==1){  // 원래 파일이 있고 새 파일이 없는 경우
            serviceRepository.modifyServicesNoFile(service);
        }else{ //selectedFile.length()<1 => 파일이 있었는데 삭제된 경우 
            service.setFileName(serviceDTO.getFileName());
            serviceRepository.modifyServicesDeleteFile(service);
        }
        
    }

    // 게시물 검색
    public List<ServiceDTO> searchService(String type, String keyword, String date, UserAuth sAuth, SCate sCate){

        List<com.omakase.omastay.entity.Service> services = null;
        
        String startDate = null;
        String endDate = null;

        if(!date.isEmpty()){
            String[] date2 = date.split(" ~ ");
            startDate = date2[0];
            endDate = date2[1];
        }
        
        services = serviceRepository.searchServices(type, keyword, startDate, endDate, sAuth, sCate); 
        
        return ServiceMapper.INSTANCE.toServiceDTOList(services);
    }

    // 호스트 공지사항 검색(선영언니꺼)
    public List<ServiceDTO> searchHostNotice(String type, String keyword, UserAuth sAuth, SCate sCate){
        List<com.omakase.omastay.entity.Service> services;

        services = serviceRepository.searchHostNotice(type, keyword, sAuth, sCate);
 
        return ServiceMapper.INSTANCE.toServiceDTOList(services);
    }

    public ServiceDTO getEvent(int id) {
        Optional<com.omakase.omastay.entity.Service> serviceOpt = serviceRepository.findByIdAndSCate(id, SCate.EVENT);
          return serviceOpt.map(ServiceMapper.INSTANCE::toServiceDTO)
                     .orElseThrow(() -> new EntityNotFoundException("Event not found"));
    }

    
}
