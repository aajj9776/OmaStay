package com.omakase.omastay.dto.custom;

import com.omakase.omastay.vo.StartEndVo;

import jakarta.validation.constraints.Email;
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
    private String keyword;
    private Integer startPrice;
    private Integer endPrice;
    private Integer person;
    private StartEndVo startEndDay;
    private String filter;
}
