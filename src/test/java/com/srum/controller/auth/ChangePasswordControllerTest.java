package com.srum.controller.auth;

import com.srum.entity.ClassAdmin;
import com.srum.main.SRUMApplication;
import com.srum.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class ChangePasswordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder encoder;

    private MultiValueMap<String, String> data;

    @BeforeEach
    void setUp() {
        data = new LinkedMultiValueMap<>();
        data.add("account", "abc");
        data.add("password", "12345678");
        data.add("newPassword", "abcd1234");
    }

    @Test
    void indexTest() throws Exception {
        mockMvc.perform(get("/change-password"))
                .andExpect(view().name("change-password"))
                .andExpect(model().attributeExists("account"));
    }

    @Test
    void updateHasErrors() throws Exception {
        data.remove("account");

        mockMvc.perform(put("/change-password").params(data))
                .andExpect(view().name("change-password"));
    }

    @Test
    void updateInvalidAccount() throws Exception {
        when(userService.getUserByAccount(data.get("account").get(0))).thenReturn(null);

        mockMvc.perform(put("/change-password").params(data))
                .andExpect(view().name("change-password"));
    }

    @Test
    void updateInvalidPassword() throws Exception {
        ClassAdmin classAdmin = new ClassAdmin();

        when(userService.getUserByAccount(data.get("account").get(0))).thenReturn(classAdmin);
        when(encoder.matches(data.get("password").get(0), classAdmin.getPassword())).thenReturn(false);

        mockMvc.perform(put("/change-password").params(data))
                .andExpect(view().name("change-password"));
    }

    @Test
    void updateSuccessfully() throws Exception {
        ClassAdmin classAdmin = new ClassAdmin();
        when(userService.getUserByAccount(data.get("account").get(0))).thenReturn(classAdmin);
        when(encoder.matches(data.get("password").get(0), classAdmin.getPassword())).thenReturn(true);

        mockMvc.perform(put("/change-password").params(data))
                .andExpect(redirectedUrl("/login"));
    }
}