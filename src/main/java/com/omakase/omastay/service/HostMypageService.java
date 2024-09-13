package com.omakase.omastay.service;

import org.apache.tomcat.util.http.parser.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.omakase.omastay.dto.AccountDTO;
import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.dto.HostInfoDTO;
import com.omakase.omastay.dto.custom.HostMypageDTO;
import com.omakase.omastay.entity.Account;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.enumurate.HStep;
import com.omakase.omastay.mapper.AccountMapper;
import com.omakase.omastay.mapper.AdminMemberMapper;
import com.omakase.omastay.mapper.HostInfoMapper;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.repository.AccountRepository;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.repository.AdminMemberRepository;

@Service
public class HostMypageService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HostInfoRepository hostInfoRepository;


    public void saveHostMypage(HostMypageDTO hostMypageDTO, AdminMemberDTO adminMemberDTO) {
        System.out.println(hostMypageDTO);

        System.out.println(adminMemberDTO);
        
        AdminMember adminMember = AdminMemberMapper.INSTANCE.toAdminMember(adminMemberDTO);
        
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());
        if (hostInfo == null) {
            hostInfo = new HostInfo();
            hostInfo.setAdminMember(adminMember); // AdminMember 설정
            hostInfo.setHStep(HStep.MYPAGE); // hStep을 0으로 설정
        }
        hostInfo.setHostContactInfo(hostMypageDTO.getHostInfo().getHostContactInfo());
        hostInfo.setHurl(hostMypageDTO.getHostInfo().getHurl());
        hostInfo.setHname(hostMypageDTO.getHostInfo().getHname());
        hostInfo.setHphone(hostMypageDTO.getHostInfo().getHphone());
        hostInfoRepository.save(hostInfo);

        Account account = accountRepository.findByHostInfoId(hostInfo.getId());
        if(account == null) {
            account = new Account();
            account.setHostInfo(hostInfo);
        }
        account.setAcBank(hostMypageDTO.getAccount().getAcBank());
        account.setAcName(hostMypageDTO.getAccount().getAcName());
        account.setAcNum(hostMypageDTO.getAccount().getAcNum());
        accountRepository.save(account); 
    }

    public HostMypageDTO findHostMypageByAdminMember(AdminMemberDTO adminMember) {
        System.out.println("마이페이지 서비스 왔다");
        HostInfo hostInfo = hostInfoRepository.findByAdminMemberId(adminMember.getId());
        System.out.println(hostInfo);
        Account account = null;
        if (hostInfo != null) {
            account = accountRepository.findByHostInfoId(hostInfo.getId());
        }

        AccountDTO accountDTO = null;
        HostInfoDTO hostInfoDTO = null;

        if (account != null) {
            try {
                accountDTO = AccountMapper.INSTANCE.toAccountDTO(account);
                System.out.println(accountDTO);
                if (account.getHostInfo() != null) {
                    System.out.println(account.getHostInfo());
                } else {
                    System.out.println("HostInfo is null for the given account");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if (hostInfo != null) {
            try {
                hostInfoDTO = HostInfoMapper.INSTANCE.toHostInfoDTO(hostInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return new HostMypageDTO(accountDTO, hostInfoDTO);
    }
}