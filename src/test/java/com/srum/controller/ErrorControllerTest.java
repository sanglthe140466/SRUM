package com.srum.controller;

import com.srum.main.SRUMApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class ErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void accessDenied() throws Exception {
        mockMvc.perform(get("/403"))
                .andExpect(view().name("error/403"));
    }
}