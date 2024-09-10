package com.omakase.omastay.controller;

import com.omakase.omastay.dto.custom.FilterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/search")
public class SearchController {

        @RequestMapping("/detail-host")
        public String detailHost() {
            return "search/detail_host";
        }

        @RequestMapping("/domestic-accommodations")
        public String domesticAccommodations() {
            return "search/domestic-accommodations";
        }

    @PostMapping(value = "/filtering")
    @ResponseBody
    public FilterDTO filtering(@RequestBody FilterDTO filterDTO) {
        System.out.println(filterDTO);
        for(int i : filterDTO.getFacilities()) {
            System.out.println("idx" + i);
        }
        System.out.println("hCate" + filterDTO.getHCate());
        System.out.println("keyword" + filterDTO.getKeyword());
        System.out.println("startPrice" + filterDTO.getStartPrice());
        System.out.println("endPrice" + filterDTO.getEndPrice());
        System.out.println("startDay" + filterDTO.getStartEndDay().getStart());
        System.out.println("endDay" + filterDTO.getStartEndDay().getEnd());
        System.out.println("filter" + filterDTO.getFilter());

        return filterDTO;
    }
}
