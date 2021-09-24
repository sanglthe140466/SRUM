package com.srum.controller;

import com.srum.entity.Issue;
import com.srum.entity.Solution;
import com.srum.main.SRUMApplication;
import com.srum.service.IssueService;
import com.srum.service.SolutionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author ThoNN1
 */
@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class SolutionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IssueService issueService;

    @MockBean
    private SolutionService solutionService;

    @Test
    void getFormAddSolution() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("/solutions/issues/" + id))
                .andExpect(status().isOk())
                .andExpect(model().attribute("issueId", id))
                .andExpect(view().name("component/issue :: formCreateSolution"));
    }

    @Test
    void storeSuccessTest() throws Exception {
        Long issueId = 1L;
        String content = "issue content";
        Issue issue = new Issue();

        when(issueService.getIssueById(issueId)).thenReturn(issue);

        mockMvc.perform(post("/solutions/issues/" + issueId).param("content", content).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(content)));
    }

    @Test
    void storeIssueNotFoundTest() throws Exception {
        Long issueId = 1L;
        String content = "issue content";

        when(issueService.getIssueById(issueId)).thenReturn(null);

        mockMvc.perform(post("/solutions/issues/" + issueId).param("content", content).with(csrf()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getEditFormTest() throws Exception {
        Long solutionId = 1L;
        Solution solution = new Solution();

        when(solutionService.getSolutionById(solutionId)).thenReturn(solution);

        mockMvc.perform(get("/solutions/" + solutionId))
                .andExpect(status().isOk())
                .andExpect(view().name("component/issue :: formEditSolution"))
                .andExpect(model().attribute("solution", solution));

    }

    @Test
    void getEditFormNotFoundTest() throws Exception {
        Long id = 1L;

        when(solutionService.getSolutionById(id)).thenReturn(null);

        mockMvc.perform(get("/solutions/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateSuccessTest() throws Exception {
        Long id = 1L;
        String content = "New solution content";
        Solution solution = new Solution();

        when(solutionService.getSolutionById(id)).thenReturn(solution);

        mockMvc.perform(put("/solutions/" + id).param("content", content))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(content)));
    }

    @Test
    void updateSolutionNotFound() throws Exception {
        Long id = 1L;
        String content = "New solution content";

        when(solutionService.getSolutionById(id)).thenReturn(null);

        mockMvc.perform(put("/solutions/" + id).param("content", content))
                .andExpect(status().isNotFound());
    }
}