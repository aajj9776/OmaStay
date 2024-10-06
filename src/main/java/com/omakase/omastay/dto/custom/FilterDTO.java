package com.omakase.omastay.dto.custom;

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
    @NotNull
    private StartEndVo startEndDay;
    @NotNull
    private Integer person;
    private Integer startPrice;
    private Integer endPrice;
    private List<Integer> facilities;
    private Integer hCate;
    private String filter;
}