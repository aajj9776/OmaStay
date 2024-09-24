package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.entity.HostInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:37+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class HostInfoMapperImpl implements HostInfoMapper {

    @Override
    public HostInfoDTO toHostInfoDTO(HostInfo hostInfo) {
        if ( hostInfo == null ) {
            return null;
        }

        HostInfoDTO hostInfoDTO = new HostInfoDTO();

        hostInfoDTO.setAdIdx( hostInfoAdminMemberId( hostInfo ) );
        hostInfoDTO.setAddressVo( hostInfo.getHostAddress() );
        hostInfoDTO.setHostOwnerInfo( hostInfo.getHostOwnerInfo() );
        hostInfoDTO.setId( hostInfo.getId() );
        hostInfoDTO.setAdminMember( hostInfo.getAdminMember() );
        hostInfoDTO.setRegion( hostInfo.getRegion() );
        hostInfoDTO.setHCate( hostInfo.getHCate() );
        hostInfoDTO.setXAxis( hostInfo.getXAxis() );
        hostInfoDTO.setYAxis( hostInfo.getYAxis() );
        hostInfoDTO.setHostContactInfo( hostInfo.getHostContactInfo() );
        hostInfoDTO.setHurl( hostInfo.getHurl() );
        hostInfoDTO.setCheckin( hostInfo.getCheckin() );
        hostInfoDTO.setCheckout( hostInfo.getCheckout() );
        hostInfoDTO.setDirections( hostInfo.getDirections() );
        hostInfoDTO.setRules( hostInfo.getRules() );
        hostInfoDTO.setPriceAdd( hostInfo.getPriceAdd() );
        hostInfoDTO.setHStatus( hostInfo.getHStatus() );
        hostInfoDTO.setHStep( hostInfo.getHStep() );
        hostInfoDTO.setHNone( hostInfo.getHNone() );
        hostInfoDTO.setHname( hostInfo.getHname() );
        hostInfoDTO.setHphone( hostInfo.getHphone() );

        return hostInfoDTO;
    }

    @Override
    public HostInfo toHostInfo(HostInfoDTO hostInfoDTO) {
        if ( hostInfoDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setAdminMember( hostInfoDTOToAdminMember( hostInfoDTO ) );
        hostInfo.setHostAddress( hostInfoDTO.getAddressVo() );
        hostInfo.setHostOwnerInfo( hostInfoDTO.getHostOwnerInfo() );
        hostInfo.setId( hostInfoDTO.getId() );
        hostInfo.setRegion( hostInfoDTO.getRegion() );
        hostInfo.setHCate( hostInfoDTO.getHCate() );
        hostInfo.setXAxis( hostInfoDTO.getXAxis() );
        hostInfo.setYAxis( hostInfoDTO.getYAxis() );
        hostInfo.setHostContactInfo( hostInfoDTO.getHostContactInfo() );
        hostInfo.setHurl( hostInfoDTO.getHurl() );
        hostInfo.setCheckin( hostInfoDTO.getCheckin() );
        hostInfo.setCheckout( hostInfoDTO.getCheckout() );
        hostInfo.setDirections( hostInfoDTO.getDirections() );
        hostInfo.setRules( hostInfoDTO.getRules() );
        hostInfo.setPriceAdd( hostInfoDTO.getPriceAdd() );
        hostInfo.setHStatus( hostInfoDTO.getHStatus() );
        hostInfo.setHStep( hostInfoDTO.getHStep() );
        hostInfo.setHNone( hostInfoDTO.getHNone() );
        hostInfo.setHname( hostInfoDTO.getHname() );
        hostInfo.setHphone( hostInfoDTO.getHphone() );

        return hostInfo;
    }

    @Override
    public List<HostInfoDTO> toHostInfoDTOList(List<HostInfo> hostInfos) {
        if ( hostInfos == null ) {
            return null;
        }

        List<HostInfoDTO> list = new ArrayList<HostInfoDTO>( hostInfos.size() );
        for ( HostInfo hostInfo : hostInfos ) {
            list.add( toHostInfoDTO( hostInfo ) );
        }

        return list;
    }

    @Override
    public List<HostInfo> toHostInfoList(List<HostInfoDTO> hostInfoDTOs) {
        if ( hostInfoDTOs == null ) {
            return null;
        }

        List<HostInfo> list = new ArrayList<HostInfo>( hostInfoDTOs.size() );
        for ( HostInfoDTO hostInfoDTO : hostInfoDTOs ) {
            list.add( toHostInfo( hostInfoDTO ) );
        }

        return list;
    }

    private Integer hostInfoAdminMemberId(HostInfo hostInfo) {
        if ( hostInfo == null ) {
            return null;
        }
        AdminMember adminMember = hostInfo.getAdminMember();
        if ( adminMember == null ) {
            return null;
        }
        Integer id = adminMember.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected AdminMember hostInfoDTOToAdminMember(HostInfoDTO hostInfoDTO) {
        if ( hostInfoDTO == null ) {
            return null;
        }

        AdminMember.AdminMemberBuilder adminMember = AdminMember.builder();

        adminMember.id( hostInfoDTO.getAdIdx() );

        return adminMember.build();
    }
}
