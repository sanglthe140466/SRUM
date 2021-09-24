package com.srum.controller.auth;

import com.srum.entity.User;
import com.srum.util.Roles;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ThoNN1
 */
@Controller
public class DefaultController {
    @RequestMapping("/default")
    public String defaultRedirect(Authentication authentication) {
        if (authentication != null && authentication.getAuthorities().stream().anyMatch(grantedAuthority -> Roles.TRAINEE.equals(grantedAuthority.getAuthority()))) {
            return "redirect:/trainee/" + ((User) authentication.getPrincipal()).getId();
        } else {
            return "redirect:/";
        }
    }
}
