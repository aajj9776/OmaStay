package com.omakase.omastay.dto.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Top5SalesDTO {
    private String hostName;
    private Integer totalSales;
}
