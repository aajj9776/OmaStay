package com.omakase.omastay.dto.custom;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.tomcat.util.http.parser.Host;


import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.enumurate.ResStatus;
import com.omakase.omastay.vo.HostContactInfoVo;
import com.omakase.omastay.vo.StartEndVo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HostReservationEmailDTO {
    private Integer id;
    private Integer hostIdx;
    private String hName;
    private String contactName;
    private String contactEmail;
    private Integer roomIdx;
    private String roomName;
    private Integer payIdx;
    private String resNum;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer resPerson;
    private Integer resPrice;
    private ResStatus resStatus;
    private String resName;
    private String resEmail;
    private LocalDateTime payDate;
    private String payMethod;
    private String nsalePrice;
    private String paymentKey;


    public HostReservationEmailDTO(Reservation reservation) {
        this.id = reservation.getId();
        this.hostIdx = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getHostInfo().getId() : null;
        this.hName = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getHostInfo().getHname() : null;
        this.contactName = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getHostInfo().getHostContactInfo().getContactName() : null;
        this.contactEmail = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getHostInfo().getHostContactInfo().getContactEmail() : null;
        this.roomIdx = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getId() : null;
        this.roomName = reservation.getRoomInfo() != null ? reservation.getRoomInfo().getRoomName() : null; 
        this.payIdx = reservation.getPayment() != null ? reservation.getPayment().getId() : null;
        this.resNum = reservation.getResNum();
        this.startDate = reservation.getStartEndVo().getStart().toLocalDate();
        this.endDate = reservation.getStartEndVo().getEnd().toLocalDate();
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
    
}
