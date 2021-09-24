package com.srum.controller.auth;

import com.srum.entity.PasswordResetToken;
import com.srum.entity.User;
import com.srum.main.SRUMApplication;
import com.srum.service.ResetPasswordService;
import com.srum.service.UserService;
import com.srum.util.Constants;
import com.srum.util.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class ResetPasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ResetPasswordService resetPasswordService;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/password/reset"))
                .andExpect(view().name("reset-password"));
    }

    @Test
    void getResetAccountNotFound() throws Exception {
        String account = "abc";

        when(userService.getUserByAccount(account)).thenReturn(null);

        mockMvc.perform(post("/password/reset").param("account", account))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString(Messages.ACCOUNT_NOT_FOUND)));
    }

    @Test
    void getResetSuccess() throws Exception {
        String account = "abc";
        User user = new User();
        user.setPassword("password");

        when(userService.getUserByAccount(account)).thenReturn(user);

        mockMvc.perform(post("/password/reset").param("account", account))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Messages.CHECK_EMAIL)));
    }

    @Test
    void resetPageTokenNotFound() throws Exception {
        String token = "reset-password-token";

        when(resetPasswordService.findByToken(token)).thenReturn(null);

        mockMvc.perform(get("/password/reset/" + token))
                .andExpect(view().name("error/404"));
    }

    @Test
    void resetPageSuccess() throws Exception {
        String token = "XoGZuTU3i20evqla1XL4xdmS4zly8ETtzg.ZFhkoG62";
        User user = new User();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setExpiryDate(LocalDate.now().plusDays(Constants.EXPIRY_TOKEN_DAY));
        passwordResetToken.setUser(user);

        when(resetPasswordService.findByToken(token)).thenReturn(passwordResetToken);

        mockMvc.perform(get("/password/reset/" + token))
                .andExpect(view().name("reset-password-page"))
                .andExpect(model().attribute("token", token))
                .andExpect(model().attribute("user", passwordResetToken.getUser()));
    }

    @Test
    void resetInvalidToken() throws Exception {
        String token = "XoGZuTU3i20evqla1XL4xdmS4zly8ETtzg.ZFhkoG62";

        when(resetPasswordService.findByToken(token)).thenReturn(null);

        mockMvc.perform(put("/password/reset/" + token).param("password", "password"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString(Messages.INVALID_TOKEN)));
    }

    @Test
    void resetSuccess() throws Exception {
        String token = "XoGZuTU3i20evqla1XL4xdmS4zly8ETtzg.ZFhkoG62";
        User user = new User();
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setExpiryDate(LocalDate.now().plusDays(Constants.EXPIRY_TOKEN_DAY));
        passwordResetToken.setUser(user);

        when(resetPasswordService.findByToken(token)).thenReturn(passwordResetToken);

        mockMvc.perform(put("/password/reset/" + token).param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Messages.UPDATED_PASSWORD)));
    }
}