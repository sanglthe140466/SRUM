package com.srum.controller;

import com.srum.main.SRUMApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * AUTHOR:CuongLH6
 * CREATED DATE: 14/09/2021
 */
@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
public class MistakeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_show_mistake_list_page() throws Exception {
        mockMvc.perform(get("/trainee/{traineeId}/mistake",3L))
                .andExpect(view().name("component/view-mistake"));

    }
}
