package com.omakase.omastay.controller;

import com.omakase.omastay.dto.custom.AccommodationResponseDTO;
import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.service.FacilitiesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private final FacilitiesService facilitiesService;

    public SearchController(FacilitiesService facilitiesService) {
        this.facilitiesService = facilitiesService;
    }

    //숙소 세부사항 창
    @RequestMapping("/detail-host")
    public ModelAndView detailHost() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("search/detail_host");
        mv.addObject("includeSearchBar", true);
        return mv;
    }

    @GetMapping(value = "/search")
    public ModelAndView search(@ModelAttribute @Valid FilterDTO search,
                               @RequestParam(name = "page", defaultValue = "1") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size)
    {
        Pageable pageable = PageRequest.of(page - 1, size);
        AccommodationResponseDTO accommodationResponseDTO = facilitiesService.search(search, pageable);

        List<ResultAccommodationsDTO> resultAccommodations = accommodationResponseDTO.getAccommodations();


        System.out.println(resultAccommodations);

        ModelAndView mv = new ModelAndView();
        mv.addObject("resultAccommodations", resultAccommodations);
        mv.addObject("pageNation", accommodationResponseDTO.getPageNation());
        mv.addObject("includeSearchBar", true);
        mv.setViewName("search/domestic-accommodations");

        return mv;
    }

    //숙소 검색 필터링
    public FilterDTO filtering(@RequestBody @Valid FilterDTO filterDTO) {
        System.out.println(filterDTO);
        List<ResultAccommodationsDTO> resultAccommodations = facilitiesService.filteringAccommodations(filterDTO);
        return null;
    }
}
