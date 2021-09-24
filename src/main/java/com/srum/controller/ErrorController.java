package com.srum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ThoNN1
 */
@Controller
public class ErrorController {
    @GetMapping("/403")
    public String accessDenied() {
        return "error/403";
    }
}
