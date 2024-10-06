package com.omakase.omastay.repository.custom;

import java.util.List;

import com.omakase.omastay.entity.Service;
import com.omakase.omastay.entity.enumurate.BooleanStatus;
import com.omakase.omastay.entity.enumurate.SCate;
import com.omakase.omastay.entity.enumurate.UserAuth;

public interface ServiceRepositoryCustom {

    List<Service> findBySCateAndSAuth(SCate sCate, UserAuth sAuth, BooleanStatus sStatus);

    List<Service> searchServices(String type, String keyword, String startDate, String endDate, UserAuth sAuth, SCate sCate);

    List<Service> searchHostNotice(String type, String keyword, UserAuth sAuth, SCate sCate);

}
