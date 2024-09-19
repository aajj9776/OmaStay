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
    private List<Integer> facilities;
    private Integer hCate;
    @NotNull
    private String keyword;
    private Integer startPrice;
    private Integer endPrice;
    @NotNull
    private Integer person;
    @NotNull
    private StartEndVo startEndDay;
    private String filter;
}