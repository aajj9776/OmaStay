package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.entity.Account;
import com.omakase.omastay.entity.HostInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:36+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO toAccountDTO(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDTO accountDTO = new AccountDTO();

        accountDTO.setHIdx( accountHostInfoId( account ) );
        accountDTO.setId( account.getId() );
        accountDTO.setAcNum( account.getAcNum() );
        accountDTO.setAcBank( account.getAcBank() );
        accountDTO.setAcName( account.getAcName() );
        accountDTO.setAcNone( account.getAcNone() );

        return accountDTO;
    }

    @Override
    public Account toAccount(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        Account account = new Account();

        account.setHostInfo( accountDTOToHostInfo( accountDTO ) );
        account.setId( accountDTO.getId() );
        account.setAcNum( accountDTO.getAcNum() );
        account.setAcBank( accountDTO.getAcBank() );
        account.setAcName( accountDTO.getAcName() );
        account.setAcNone( accountDTO.getAcNone() );

        return account;
    }

    @Override
    public List<AccountDTO> toAccountDTOList(List<Account> accountList) {
        if ( accountList == null ) {
            return null;
        }

        List<AccountDTO> list = new ArrayList<AccountDTO>( accountList.size() );
        for ( Account account : accountList ) {
            list.add( toAccountDTO( account ) );
        }

        return list;
    }

    @Override
    public List<Account> toAccountList(List<AccountDTO> accountDTOList) {
        if ( accountDTOList == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( accountDTOList.size() );
        for ( AccountDTO accountDTO : accountDTOList ) {
            list.add( toAccount( accountDTO ) );
        }

        return list;
    }

    private Integer accountHostInfoId(Account account) {
        if ( account == null ) {
            return null;
        }
        HostInfo hostInfo = account.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected HostInfo accountDTOToHostInfo(AccountDTO accountDTO) {
        if ( accountDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( accountDTO.getHIdx() );

        return hostInfo;
    }
}
