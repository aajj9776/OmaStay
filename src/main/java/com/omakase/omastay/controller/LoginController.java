package com.omakase.omastay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String login() {
        return "login/login";
    }

    @RequestMapping("/user")
    public String signup() {
        return "login/user_login";
    }

    @RequestMapping("/user/register")
    public String register() {
        return "login/user_register";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "login/admin_login";
    }

    @RequestMapping("/userfindpw")
    public String findpw() {
        return "login/userfindpw";
    }

    @RequestMapping("/userchangepw")
    public String userchangepw() {
        return "login/userchangepw";
    }


}
