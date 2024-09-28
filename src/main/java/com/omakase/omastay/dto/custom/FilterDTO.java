package com.omakase.omastay.dto.custom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.omakase.omastay.entity.enumurate.HCate;
import com.omakase.omastay.vo.StartEndVo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    @NotNull
    private String keyword;
    private StartEndVo startEndDay;
    @NotNull
    private Integer person;
    private Integer startPrice;
    private Integer endPrice;
    private List<Integer> facilities;
    private HCate hCate;
    private String filter;
    private String sortType;
    boolean soldOut;
}