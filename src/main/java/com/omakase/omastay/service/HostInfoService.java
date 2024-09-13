package com.omakase.omastay.service;

import com.omakase.omastay.dto.AdminMemberDTO;
import com.omakase.omastay.entity.AdminMember;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.repository.HostInfoRepository;
import com.omakase.omastay.vo.UserProfileVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HostInfoService {

    @Autowired
    private HostInfoRepository hostInfoRepository;

}
