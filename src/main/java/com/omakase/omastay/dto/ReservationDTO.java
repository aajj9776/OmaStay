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
    private int id;
    private int roomIdx;
    private int memIdx;
    private int nonIdx;
    private int payIdx;
    private String resNum;
    private StartEndVo startEndVo = new StartEndVo();
    private int resPerson;
    private int resPrice;
    private ResStatus resStatus;
    private String resName;
    private String resEmail;
    private String resNone;

    public ReservationDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.roomIdx = reservation.getRoomFacility() != null ? reservation.getRoomFacility().getId() : null;
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
    public ReservationDTO(int id, int roomIdx, int memIdx, int nonIdx, int paymentId, String resNum,
                          StartEndVo startEndVo, String resName, String resEmail,
                        int resPerson, int resPrice, ResStatus resStatus, String resNone) {
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