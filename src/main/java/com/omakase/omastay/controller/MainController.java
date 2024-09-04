package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping()
    public String index(@RequestParam(value = "activeSearch", required = false)
                            String activeSearch, Model model) {
        model.addAttribute("activeSearch", activeSearch);

        return "index";
    }


}
