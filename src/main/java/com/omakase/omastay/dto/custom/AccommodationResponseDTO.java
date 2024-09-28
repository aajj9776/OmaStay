package com.omakase.omastay.dto.custom;

import lombok.Data;

import java.util.List;

@Data
public class AccommodationResponseDTO {

    private List<ResultAccommodationsDTO> accommodations;

    private PageNation pageNation;

}
