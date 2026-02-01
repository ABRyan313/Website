package com.example.Website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping({
            "/",
            "/admin",
            "/admin/**",
            "/projects",
            "/about",
            "/contact"
    })
    public String forwardReactRoutes() {
        return "forward:/index.html";
    }
}
