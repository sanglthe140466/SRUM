package com.srum.controller;

import com.srum.entity.User;
import com.srum.main.SRUMApplication;
import com.srum.util.Messages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * AUTHOR:CuongLH6
 * CREATED DATE: 14/09/2021
 */
@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
public class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    private User user;
    
    @Test
    void test_show_comment_list_page() throws Exception {
        mockMvc.perform(get("/trainee/{traineeId}/comment",3L))
                .andExpect(view().name("component/view-comment"));

    }

    @Test
    void test_save_comment_with_input_is_valid() throws Exception {
        user.setId(1L);
        mockMvc.perform(post("/trainee/{traineeId}/comment/create",3L)
                .contentType(MediaType.ALL)
                .param("comment", "C01")

                .param("user.id", String.valueOf(user.getId()))
        )
                .andExpect(view().name("component/view-comment"));
    }

}
