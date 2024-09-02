package com.omakase.omastay.repository;
import com.omakase.omastay.entity.Account;
import com.omakase.omastay.repository.custom.AccountRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, String>, AccountRepositoryCustom {

}