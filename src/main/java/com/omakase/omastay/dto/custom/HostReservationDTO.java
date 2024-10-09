package com.omakase.omastay.dto.custom;

import java.time.LocalDateTime;

import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.vo.StartEndVo;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostReservationDTO {
    private Integer id;
    private Integer roomIdx;
    private String roomName;
    private Integer payIdx;
    private String resNum;
    private StartEndVo startEndVo = new StartEndVo();
    private Integer resPerson;
    private Integer resPrice;
    private ResStatus resStatus;
    private String resName;
    private String resEmail;
    private LocalDateTime payDate;
    private String payMethod;
    private String nsalePrice;
    private String paymentKey;


    public HostReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.roomIdx = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getId() : null;
        this.roomName = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getRoomName() : null; 
        this.payIdx = reservation.getPayment() != null ? reservation.getPayment().getId() : null;
        this.resNum = reservation.getResNum();
        this.startEndVo = reservation.getStartEndVo();
        this.resPerson = reservation.getResPerson();
        this.resPrice = reservation.getResPrice();
        this.resStatus = reservation.getResStatus();
        this.resName = reservation.getResName();
        this.resEmail = reservation.getResEmail();
        this.payDate = reservation.getPayment() != null ? reservation.getPayment().getPayDate() : null;
        this.payMethod = reservation.getPayment() != null ? reservation.getPayment().getPayMethod().name() : null;
        this.nsalePrice = reservation.getPayment() != null ? reservation.getPayment().getNsalePrice() : null;
        this.paymentKey = reservation.getPayment() != null ? reservation.getPayment().getPaymentKey() : null;
    }

    @QueryProjection
    public HostReservationDTO(Integer id, Integer roomIdx, String roomName, Integer paymentId, String resNum,
                          StartEndVo startEndVo, String resName, String resEmail,
                          Integer resPerson, Integer resPrice, ResStatus resStatus, LocalDateTime payDate, 
                          String payMethod, String nsalePrice, String paymentKey) {
        this.id = id;
        this.roomIdx = roomIdx;
        this.roomName = roomName; // roomName 초기화
        this.payIdx = paymentId;
        this.resNum = resNum;
        this.startEndVo = startEndVo;
        this.resPerson = resPerson;
        this.resPrice = resPrice;
        this.resName = resName;
        this.resEmail = resEmail;
        this.resStatus = resStatus;
        this.payDate = payDate;
        this.payMethod = payMethod;
        this.nsalePrice = nsalePrice;
        this.paymentKey = paymentKey;
    }
    
}
