package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.SalesDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Reservation;
import com.omakase.omastay.entity.Sales;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-23T13:57:37+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class SalesMapperImpl implements SalesMapper {

    @Override
    public SalesDTO toSalesDTO(Sales sales) {
        if ( sales == null ) {
            return null;
        }

        SalesDTO salesDTO = new SalesDTO();

        salesDTO.setHostInfoId( salesHostInfoId( sales ) );
        salesDTO.setResIdx( salesReservationId( sales ) );
        salesDTO.setId( sales.getId() );
        salesDTO.setSalDate( sales.getSalDate() );
        salesDTO.setSalNone( sales.getSalNone() );

        return salesDTO;
    }

    @Override
    public Sales toSales(SalesDTO salesDTO) {
        if ( salesDTO == null ) {
            return null;
        }

        Sales sales = new Sales();

        sales.setHostInfo( salesDTOToHostInfo( salesDTO ) );
        sales.setReservation( salesDTOToReservation( salesDTO ) );
        sales.setId( salesDTO.getId() );
        sales.setSalDate( salesDTO.getSalDate() );
        sales.setSalNone( salesDTO.getSalNone() );

        return sales;
    }

    @Override
    public List<SalesDTO> toSalesDTOList(List<Sales> salesList) {
        if ( salesList == null ) {
            return null;
        }

        List<SalesDTO> list = new ArrayList<SalesDTO>( salesList.size() );
        for ( Sales sales : salesList ) {
            list.add( toSalesDTO( sales ) );
        }

        return list;
    }

    @Override
    public List<Sales> toSalesList(List<SalesDTO> salesDTOList) {
        if ( salesDTOList == null ) {
            return null;
        }

        List<Sales> list = new ArrayList<Sales>( salesDTOList.size() );
        for ( SalesDTO salesDTO : salesDTOList ) {
            list.add( toSales( salesDTO ) );
        }

        return list;
    }

    private Integer salesHostInfoId(Sales sales) {
        if ( sales == null ) {
            return null;
        }
        HostInfo hostInfo = sales.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer salesReservationId(Sales sales) {
        if ( sales == null ) {
            return null;
        }
        Reservation reservation = sales.getReservation();
        if ( reservation == null ) {
            return null;
        }
        Integer id = reservation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected HostInfo salesDTOToHostInfo(SalesDTO salesDTO) {
        if ( salesDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( salesDTO.getHostInfoId() );

        return hostInfo;
    }

    protected Reservation salesDTOToReservation(SalesDTO salesDTO) {
        if ( salesDTO == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setId( salesDTO.getResIdx() );

        return reservation;
    }
}
