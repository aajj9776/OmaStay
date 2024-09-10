package com.omakase.omastay.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.omakase.omastay.dto.HostMypageDTO;
import com.omakase.omastay.entity.Account;
import com.omakase.omastay.entity.HostInfo;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface HostMypageMapper {

    HostMypageMapper INSTANCE = Mappers.getMapper(HostMypageMapper.class);

    @Mapping(source = "account", target = "account")
    HostMypageDTO toHostMypageDTO(Account account, HostInfo hostInfo);

    @Mapping(source = "account.acBank", target = "acBank")
    @Mapping(source = "account.acName", target = "acName")
    @Mapping(source = "account.acNum", target = "acNum")
    Account toAccountEntity(HostMypageDTO hostMypageDTO);

    HostInfo toHostInfoEntity(HostMypageDTO hostMypageDTO);

}