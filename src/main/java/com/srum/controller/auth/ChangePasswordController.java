package com.srum.controller.auth;

import com.srum.dto.ChangePasswordDTO;
import com.srum.entity.ClassAdmin;
import com.srum.entity.User;
import com.srum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author ThoNN1
 */
@Controller
@RequestMapping("/change-password")
public class ChangePasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("account", new ChangePasswordDTO());
        return "change-password";
    }

    @PutMapping
    public String update(@Valid @ModelAttribute("account") ChangePasswordDTO account, BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            return "change-password";
        }
        User user = userService.getUserByAccount(account.getAccount());
        if (user != null && user instanceof ClassAdmin) {
            ClassAdmin classAdmin = (ClassAdmin) user;
            if (encoder.matches(account.getPassword(), classAdmin.getPassword())) {
                classAdmin.setPassword(encoder.encode(account.getNewPassword()));
                userService.update(user);
                return "redirect:/default";
            } else {
                bindingResult.addError(new ObjectError("error", "Invalid password"));
            }
        } else {
            bindingResult.addError(new ObjectError("error", "Invalid account"));
        }
        model.addAttribute("account", account);
        return "change-password";
    }
}
