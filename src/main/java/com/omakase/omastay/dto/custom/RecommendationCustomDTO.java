package com.omakase.omastay.dto.custom;

import com.omakase.omastay.dto.RecommendationDTO;
import com.omakase.omastay.dto.HostInfoDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendationCustomDTO {
    private RecommendationDTO recommendationDTO;
    private HostInfoDTO hostInfoDTO;
}
