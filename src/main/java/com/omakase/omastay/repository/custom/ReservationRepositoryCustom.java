package com.omakase.omastay.repository.custom;

import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.RoomInfo;
import java.util.List;


public interface ReservationRepositoryCustom {

    List<Reservation> searchRes(String resStatus, String startDate, String endDate, RoomInfo roomInfo);

    List<Reservation> get5List(Integer memId);
}
