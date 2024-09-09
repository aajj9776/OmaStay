package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

        @RequestMapping("/filter")
        public String filter() {
            return "search/filter";
        }
}
