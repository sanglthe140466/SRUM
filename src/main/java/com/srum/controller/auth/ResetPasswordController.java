package com.srum.controller.auth;

import com.srum.entity.PasswordResetToken;
import com.srum.entity.User;
import com.srum.service.ResetPasswordService;
import com.srum.service.UserService;
import com.srum.util.Constants;
import com.srum.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author ThoNN1
 */
@Controller
public class ResetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/password/reset")
    public String index() {
        return "reset-password";
    }

    @PostMapping("/password/reset")
    public ResponseEntity<String> getReset(@RequestParam("account") String account, HttpServletRequest request) {
        User user = userService.getUserByAccount(account);
        if (user == null || user.getPassword() == null) {
            return new ResponseEntity<>(Messages.ACCOUNT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(LocalDate.now().plusDays(Constants.EXPIRY_TOKEN_DAY));
        resetPasswordService.saveToken(token);

        String url =
                request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
        resetPasswordService.sendEmail(token, user.getEmail(), url);

        return ResponseEntity.ok(Messages.CHECK_EMAIL);
    }

    @GetMapping("/password/reset/{token}")
    public String resetPage(@PathVariable("token") String token, Model model) {
        PasswordResetToken passwordResetToken = resetPasswordService.findByToken(token);
        if (passwordResetToken == null || passwordResetToken.getExpiryDate().isBefore(LocalDate.now())) {
            return "error/404";
        }
        model.addAttribute("token", token);
        model.addAttribute("user", passwordResetToken.getUser());
        return "reset-password-page";
    }

    @PutMapping("/password/reset/{token}")
    public ResponseEntity<String> reset(@PathVariable("token") String token, @RequestParam("password") String password) {
        PasswordResetToken passwordResetToken = resetPasswordService.findByToken(token);
        if (passwordResetToken == null || passwordResetToken.getExpiryDate().isBefore(LocalDate.now())) {
            return new ResponseEntity<>(Messages.INVALID_TOKEN, HttpStatus.NOT_FOUND);
        }
        User user = passwordResetToken.getUser();
        user.setPassword(encoder.encode(password));
        userService.update(user);

        return new ResponseEntity<>(Messages.UPDATED_PASSWORD, HttpStatus.OK);
    }
}
