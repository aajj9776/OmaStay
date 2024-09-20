package com.omakase.omastay.repository.custom;

import java.util.List;

import com.omakase.omastay.dto.custom.CouponHistoryDTO;

public interface IssuedCouponRepositoryCustom {

    List<CouponHistoryDTO> findCouponHistoryByCpIdx(Integer cp_idx);
}
