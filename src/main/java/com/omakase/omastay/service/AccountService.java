package com.omakase.omastay.service;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.mapper.AccountMapper;
import com.omakase.omastay.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
}
