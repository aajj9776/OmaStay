package com.omakase.omastay.repository.custom;

import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import java.util.List;


public interface ReservationRepositoryCustom {

    List<Reservation> searchRes(String resStatus, String startDate, String endDate, RoomInfo roomInfo);

    // 관리자의 회원 상세 조회에서 최근 예약 내역 5건 가져옴
    List<Reservation> get5List(Integer memId);
}
