package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "hostInfo.id", target = "HIdx")
    AccountDTO toAccountDTO(Account account);

    @Mapping(source = "HIdx", target = "hostInfo.id")
    Account toAccount(AccountDTO accountDTO);

    List<AccountDTO> toAccountDTOList(List<Account> accountList);

    List<Account> toAccountList(List<AccountDTO> accountDTOList);
}