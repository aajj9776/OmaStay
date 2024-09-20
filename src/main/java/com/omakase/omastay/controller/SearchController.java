package com.omakase.omastay.controller;

import com.omakase.omastay.dto.custom.FilterDTO;
import com.omakase.omastay.dto.custom.ResultAccommodationsDTO;
import com.omakase.omastay.service.FacilitiesService;
import jakarta.validation.Valid;
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

    //숙소 검색 창(변경 예정)
    @RequestMapping("/domestic-accommodations")
    public ModelAndView domesticAccommodations() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("includeSearchBar", true);
        mv.setViewName("search/domestic-accommodations");
        return mv;
    }


    //숙소 검색 창(위에꺼 이걸로 변경예정)
    @ GetMapping(value = "/search")
    public ModelAndView search(@ModelAttribute @Valid FilterDTO search) {
        System.out.println(search);
        List<ResultAccommodationsDTO> resultAccommodations = facilitiesService.search(search);

        System.out.println(resultAccommodations);

        ModelAndView mv = new ModelAndView();
        mv.addObject("resultAccommodations", resultAccommodations);
        mv.addObject("includeSearchBar", true);
        mv.setViewName("search/domestic-accommodations");

        return mv;
    }

    //숙소 검색 필터링
    public FilterDTO filtering(@RequestBody @Valid FilterDTO filterDTO) {
        System.out.println(filterDTO);

        List<ResultAccommodationsDTO> resultAccommodations = facilitiesService.filteringAccommodations(filterDTO);

        return filterDTO;
    }
}
