package com.omakase.omastay.dto.custom;

import java.util.List;

import com.omakase.omastay.dto.ServiceDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventPathDTO {
    private String path;
    private ServiceDTO service; 
    
}
