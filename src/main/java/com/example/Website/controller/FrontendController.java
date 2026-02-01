package com.example.Website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping({
            "/",
            "/admin/**"
    })
    public String forwardReactRoutes() {
        return "forward:/index.html";
    }
}
