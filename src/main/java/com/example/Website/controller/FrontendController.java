package com.example.Website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller that forwards non-backend requests to the Single Page Application entry point.
 *
 * <p>This forwards any request that does not start with `/api` or `h2-console` to
 * `index.html` so client-side routing can handle the path. Backend endpoints and the
 * H2 console remain accessible.
 */
@Controller
public class FrontendController {
    @GetMapping("/{path:^(?!api|h2-console).*$}")
    public String forward() {
        return "forward:/index.html";
    }
}