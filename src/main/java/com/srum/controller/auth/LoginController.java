package com.srum.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author ThoNN1
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String index() {
        return "login";
    }
}
