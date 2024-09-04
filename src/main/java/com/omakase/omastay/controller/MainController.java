package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(String activeSearch, Model model) {
        model.addAttribute("activeSearch", activeSearch);

        return "index";
    }


}
