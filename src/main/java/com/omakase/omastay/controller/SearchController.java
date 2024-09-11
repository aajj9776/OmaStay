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
            System.out.println(i);
        }
        System.out.println(filterDTO.getHCate());
        System.out.println(filterDTO.getKeyword());
        System.out.println(filterDTO.getStartPrice());
        System.out.println(filterDTO.getEndPrice());
        System.out.println(filterDTO.getFilter());



        return filterDTO;
    }
}
