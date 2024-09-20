package com.omakase.omastay.mapper;

import com.omakase.omastay.dto.CalculationDTO;
import com.omakase.omastay.entity.Calculation;
import com.omakase.omastay.entity.HostInfo;
import com.omakase.omastay.vo.StartEndVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-19T14:51:43+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.11 (Amazon.com Inc.)"
)
public class CalculationMapperImpl implements CalculationMapper {

    @Override
    public CalculationDTO toCalculationDTO(Calculation calculation) {
        if ( calculation == null ) {
            return null;
        }

        CalculationDTO calculationDTO = new CalculationDTO();

        calculationDTO.setCalStartEnd( startEndVoToStartEndVo( calculation.getCalStartEnd() ) );
        calculationDTO.setHIdx( calculationHostInfoId( calculation ) );
        calculationDTO.setId( calculation.getId() );
        calculationDTO.setCalAmount( calculation.getCalAmount() );
        calculationDTO.setCalStatus( calculation.getCalStatus() );
        calculationDTO.setCalLegTime( calculation.getCalLegTime() );
        calculationDTO.setCalConfirmTime( calculation.getCalConfirmTime() );
        calculationDTO.setCalCompleteTime( calculation.getCalCompleteTime() );
        calculationDTO.setCalCancelTime( calculation.getCalCancelTime() );
        calculationDTO.setCalNone( calculation.getCalNone() );

        return calculationDTO;
    }

    @Override
    public Calculation toCalculation(CalculationDTO calculationDTO) {
        if ( calculationDTO == null ) {
            return null;
        }

        Calculation calculation = new Calculation();

        calculation.setHostInfo( calculationDTOToHostInfo( calculationDTO ) );
        calculation.setCalStartEnd( startEndVoToStartEndVo1( calculationDTO.getCalStartEnd() ) );
        calculation.setId( calculationDTO.getId() );
        calculation.setCalAmount( calculationDTO.getCalAmount() );
        calculation.setCalStatus( calculationDTO.getCalStatus() );
        calculation.setCalLegTime( calculationDTO.getCalLegTime() );
        calculation.setCalConfirmTime( calculationDTO.getCalConfirmTime() );
        calculation.setCalCompleteTime( calculationDTO.getCalCompleteTime() );
        calculation.setCalCancelTime( calculationDTO.getCalCancelTime() );
        calculation.setCalNone( calculationDTO.getCalNone() );

        return calculation;
    }

    @Override
    public List<CalculationDTO> toCalculationDTOList(List<Calculation> calculationList) {
        if ( calculationList == null ) {
            return null;
        }

        List<CalculationDTO> list = new ArrayList<CalculationDTO>( calculationList.size() );
        for ( Calculation calculation : calculationList ) {
            list.add( toCalculationDTO( calculation ) );
        }

        return list;
    }

    @Override
    public List<Calculation> toCalculationList(List<CalculationDTO> calculationDTOList) {
        if ( calculationDTOList == null ) {
            return null;
        }

        List<Calculation> list = new ArrayList<Calculation>( calculationDTOList.size() );
        for ( CalculationDTO calculationDTO : calculationDTOList ) {
            list.add( toCalculation( calculationDTO ) );
        }

        return list;
    }

    protected StartEndVo startEndVoToStartEndVo(StartEndVo startEndVo) {
        if ( startEndVo == null ) {
            return null;
        }

        StartEndVo startEndVo1 = new StartEndVo();

        startEndVo1.setStart( startEndVo.getStart() );
        startEndVo1.setEnd( startEndVo.getEnd() );

        return startEndVo1;
    }

    private Integer calculationHostInfoId(Calculation calculation) {
        if ( calculation == null ) {
            return null;
        }
        HostInfo hostInfo = calculation.getHostInfo();
        if ( hostInfo == null ) {
            return null;
        }
        Integer id = hostInfo.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected HostInfo calculationDTOToHostInfo(CalculationDTO calculationDTO) {
        if ( calculationDTO == null ) {
            return null;
        }

        HostInfo hostInfo = new HostInfo();

        hostInfo.setId( calculationDTO.getHIdx() );

        return hostInfo;
    }

    protected StartEndVo startEndVoToStartEndVo1(StartEndVo startEndVo) {
        if ( startEndVo == null ) {
            return null;
        }

        StartEndVo startEndVo1 = new StartEndVo();

        startEndVo1.setStart( startEndVo.getStart() );
        startEndVo1.setEnd( startEndVo.getEnd() );

        return startEndVo1;
    }
}
