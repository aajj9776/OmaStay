package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.omakase.omastay.dto.ReviewDTO;
import com.omakase.omastay.service.ReviewService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/insert")
    @ResponseBody 
    public ReviewDTO addReview(@RequestBody ReviewDTO reviewDTO) { 
        ReviewDTO rdto = reviewService.addReview(reviewDTO);
        return rdto;
    }
}
