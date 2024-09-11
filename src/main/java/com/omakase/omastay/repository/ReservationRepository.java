package com.omakase.omastay.repository;

import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.repository.custom.ReservationRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>, ReservationRepositoryCustom {


}