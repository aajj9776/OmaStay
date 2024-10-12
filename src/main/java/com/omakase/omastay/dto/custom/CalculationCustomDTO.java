package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.CalculationDTO;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationCustomDTO {
    CalculationDTO calculationDTO;
    Integer hIdx;
    String hostName;
    Integer requestSum; // 요청된 정산금액
    Integer cost; // 계산된 원가
    Integer sell; // 계산된 판매액
    Integer sales; // 계산된 할인액
    Integer commission; // 수수료
    Integer calAmount; // 계산된 정산금액

    @QueryProjection
    public CalculationCustomDTO(Integer cost, Integer sales) {
        this.cost = cost;
        this.sales = sales;
    }
}
