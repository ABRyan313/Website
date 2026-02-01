package com.example.Website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping({
            "/",
            "/admin",
            "/admin/**",
            "/projects/**",
            "/about",
            "/contact"
    })
    public String index() {
        return "forward:/index.html";
    }
}