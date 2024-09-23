package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.ServiceDTO;
import com.omakase.omastay.entity.Service;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T16:50:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceDTO toServiceDTO(Service service) {
        if ( service == null ) {
            return null;
        }

        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setId( service.getId() );
        serviceDTO.setSCate( service.getSCate() );
        serviceDTO.setSAuth( service.getSAuth() );
        serviceDTO.setSTitle( service.getSTitle() );
        serviceDTO.setSContent( service.getSContent() );
        serviceDTO.setFileName( service.getFileName() );
        serviceDTO.setSDate( service.getSDate() );
        serviceDTO.setSStatus( service.getSStatus() );
        serviceDTO.setSNone( service.getSNone() );

        return serviceDTO;
    }

    @Override
    public Service toService(ServiceDTO serviceDTO) {
        if ( serviceDTO == null ) {
            return null;
        }

        Service service = new Service();

        service.setId( serviceDTO.getId() );
        service.setSCate( serviceDTO.getSCate() );
        service.setSAuth( serviceDTO.getSAuth() );
        service.setSTitle( serviceDTO.getSTitle() );
        service.setSContent( serviceDTO.getSContent() );
        service.setFileName( serviceDTO.getFileName() );
        service.setSDate( serviceDTO.getSDate() );
        service.setSStatus( serviceDTO.getSStatus() );
        service.setSNone( serviceDTO.getSNone() );

        return service;
    }

    @Override
    public List<ServiceDTO> toServiceDTOList(List<Service> serviceList) {
        if ( serviceList == null ) {
            return null;
        }

        List<ServiceDTO> list = new ArrayList<ServiceDTO>( serviceList.size() );
        for ( Service service : serviceList ) {
            list.add( toServiceDTO( service ) );
        }

        return list;
    }

    @Override
    public List<Service> toServiceList(List<ServiceDTO> serviceDTOList) {
        if ( serviceDTOList == null ) {
            return null;
        }

        List<Service> list = new ArrayList<Service>( serviceDTOList.size() );
        for ( ServiceDTO serviceDTO : serviceDTOList ) {
            list.add( toService( serviceDTO ) );
        }

        return list;
    }
}
