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
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void get_one_subject_from_subject_list_page() throws Exception {
        mockMvc.perform(get("/subject-detaild/{id}", 1L))
                .andExpect(view().name("subject-detail"));
    }

    @Test
    void get_subject_list_page() throws Exception {
        mockMvc.perform(get("/subjects-list"))
                .andExpect(view().name("subjects-list"));
    }

    @Test
    void test_not_found_subject_from_subject_list_page() throws Exception {
        mockMvc.perform(get("/subject-detaild/{id}", 0L))
                .andExpect(view().name("error/404"));
    }
}