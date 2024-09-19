package com.omakase.omastay.dto.custom;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MembercpDTO {
    
    private String id;
    private String cpContent;
    private int cpCate;
    private int cpSale;
    private int icStatus;

    
}
