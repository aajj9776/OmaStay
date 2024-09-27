package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.PaymentDTO;
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
    private Integer memIdx;
    private Integer nonIdx;
    private Integer payIdx;
    private String resNum;
    private StartEndVo startEndVo = new StartEndVo();
    private Integer resPerson;
    private Integer resPrice;
    private ResStatus resStatus;
    private String resName;
    private String resEmail;
    private String resNone;
    private PaymentDTO payment;

    public HostReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.roomIdx = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getId() : null;
        this.roomName = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getRoomName() : null; 
        this.memIdx = reservation.getMember() != null ? reservation.getMember().getId() : null;
        this.nonIdx = reservation.getNonMember() != null ? reservation.getNonMember().getId() : null;
        this.payIdx = reservation.getPayment() != null ? reservation.getPayment().getId() : null;
        this.resNum = reservation.getResNum();
        this.startEndVo = reservation.getStartEndVo();
        this.resPerson = reservation.getResPerson();
        this.resPrice = reservation.getResPrice();
        this.resStatus = reservation.getResStatus();
        this.resName = reservation.getResName();
        this.resEmail = reservation.getResEmail();
        this.resNone = reservation.getResNone();
        this.payment = new PaymentDTO(reservation.getPayment());
    }

    @QueryProjection
    public HostReservationDTO(Integer id, Integer roomIdx, String roomName, Integer memIdx, Integer nonIdx, Integer paymentId, String resNum,
                          StartEndVo startEndVo, String resName, String resEmail,
                          Integer resPerson, Integer resPrice, ResStatus resStatus, String resNone, PaymentDTO payment) {
        this.id = id;
        this.roomIdx = roomIdx;
        this.roomName = roomName; // roomName 초기화
        this.memIdx = memIdx;
        this.nonIdx = nonIdx;
        this.payIdx = paymentId;
        this.resNum = resNum;
        this.startEndVo = startEndVo;
        this.resPerson = resPerson;
        this.resPrice = resPrice;
        this.resName = resName;
        this.resEmail = resEmail;
        this.resStatus = resStatus;
        this.resNone = resNone;
        this.payment = payment;
    }
    
}
