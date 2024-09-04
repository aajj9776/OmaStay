package com.omakase.omastay.service;

import com.omakase.omastay.repository.IssuedCouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssuedCouponService {

    @Autowired
    private IssuedCouponRepository issuedCouponRepository;


}
