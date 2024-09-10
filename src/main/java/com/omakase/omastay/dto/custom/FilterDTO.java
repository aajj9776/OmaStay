package com.omakase.omastay.dto.custom;

import com.omakase.omastay.vo.StartEndVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterDTO {
    private List<Integer> facilities;
    private int hCate;
    private String keyword;
    private int startPrice;
    private int endPrice;
    private StartEndVo startEndDay;
    private String filter;
}
