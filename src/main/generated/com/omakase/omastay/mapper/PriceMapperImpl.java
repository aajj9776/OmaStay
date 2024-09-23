package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.PriceDTO;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.entity.Price;
import com.omakase.omastay.entity.RoomInfo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-22T16:50:41+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (JetBrains s.r.o.)"
)
public class PriceMapperImpl implements PriceMapper {

    @Override
    public PriceDTO toPriceDTO(Price price) {
        if ( price == null ) {
            return null;
        }

        PriceDTO priceDTO = new PriceDTO();

        priceDTO.setHIdx( priceHostInfoId( price ) );
        priceDTO.setRiIdx( priceRoomInfoId( price ) );
        priceDTO.setId( price.getId() );
        priceDTO.setRegularPrice( price.getRegularPrice() );
        priceDTO.setPeakVo( price.getPeakVo() );
        priceDTO.setSemi( price.getSemi() );
        priceDTO.setPeakSet( price.getPeakSet() );
        priceDTO.setPriNone( price.getPriNone() );

        return priceDTO;
    }

    @Override
    public Price toPrice(PriceDTO priceDTO) {
        if ( priceDTO == null ) {
            return null;
        }

        Price price = new Price();

        price.setHostInfo( priceDTOToHostInfo( priceDTO ) );
        price.setRoomInfo( priceDTOToRoomInfo( priceDTO ) );
        price.setId( priceDTO.getId() );
        price.setRegularPrice( priceDTO.getRegularPrice() );
        price.setPeakVo( priceDTO.getPeakVo() );
        price.setSemi( priceDTO.getSemi() );
        price.setPeakSet( priceDTO.getPeakSet() );
        price.setPriNone( priceDTO.getPriNone() );

        return price;
    }

    @Override
    public List<PriceDTO> toPriceDTOList(List<Price> priceList) {
        if ( priceList == null ) {
            return null;
        }

        List<PriceDTO> list = new ArrayList<PriceDTO>( priceList.size() );
        for ( Price price : priceList ) {
            list.add( toPriceDTO( price ) );
        }

        return list;
    }

    @Override
    public List<Price> toPriceList(List<PriceDTO> priceDTOList) {
        if ( priceDTOList == null ) {
            return null;
        }

        List<Price> list = new ArrayList<Price>( priceDTOList.size() );
        for ( PriceDTO priceDTO : priceDTOList ) {
            list.add( toPrice( priceDTO ) );
        }

        return list;
    }

    private Integer priceHostInfoId(Price price) {
        if ( price == null ) {
            return null;
        }
        HostInfo hostInfo = price.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer priceRoomInfoId(Price price) {
        if ( price == null ) {
            return null;
        }
        RoomInfo roomInfo = price.getRoomInfo();
        if ( roomInfo == null ) {
            return null;
        }
        Integer id = roomInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected HostInfo priceDTOToHostInfo(PriceDTO priceDTO) {
        if ( priceDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( priceDTO.getHIdx() );

        return hostInfo;
    }

    protected RoomInfo priceDTOToRoomInfo(PriceDTO priceDTO) {
        if ( priceDTO == null ) {
            return null;
        }

        RoomInfo roomInfo = new RoomInfo();

        roomInfo.setId( priceDTO.getRiIdx() );

        return roomInfo;
    }
}
