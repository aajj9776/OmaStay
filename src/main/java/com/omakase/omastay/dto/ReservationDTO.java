package com.omakase.omastay.dto;

import com.omakase.omastay.entity.*;
import com.omakase.omastay.vo.StartEndVo;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReservationDTO {
    private Integer id;
    private Integer roomIdx;
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

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.roomIdx = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getId() : null;
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
    }

    @QueryProjection
    public ReservationDTO(Integer id, Integer roomIdx, Integer memIdx, Integer nonIdx, Integer paymentId, String resNum,
                          StartEndVo startEndVo, String resName, String resEmail,
                        Integer resPerson, Integer resPrice, ResStatus resStatus, String resNone) {
        this.id = id;
        this.roomIdx = roomIdx;
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
    }
}