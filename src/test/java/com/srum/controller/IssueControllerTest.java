package com.srum.controller;

import com.srum.entity.Clazz;
import com.srum.entity.Issue;
import com.srum.main.SRUMApplication;
import com.srum.service.ClassService;
import com.srum.service.IssueService;
import com.srum.util.Messages;
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

@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassService classService;

    @MockBean
    private IssueService issueService;

    @Test
    void getAllIssues() throws Exception {
        Long id = 1L;
        Clazz clazz = new Clazz();

        when(classService.findClassById(id)).thenReturn(clazz);

        mockMvc.perform(get("/class/" + id + "/issues"))
                .andExpect(status().isOk())
                .andExpect(view().name("list-issues"))
                .andExpect(model().attribute("clazz", clazz))
                .andExpect(model().attributeExists("issueDateComparator"))
                .andExpect(model().attributeExists("solutionDateComparator"));
    }

    @Test
    void saveIssueSuccess() throws Exception {
        Long classId = 1L;
        Clazz clazz = new Clazz();
        String content = "New issue Content";

        when(classService.findClassById(classId)).thenReturn(clazz);

        mockMvc.perform(post("/class/" + classId + "/issues").param("content", content).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(content)));
    }

    @Test
    void saveIssueClassNotFound() throws Exception {
        Long classId = 1L;
        String content = "New issue Content";

        when(classService.findClassById(classId)).thenReturn(null);

        mockMvc.perform(post("/class/" + classId + "/issues").param("content", content).with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString(Messages.CLASS_NOT_FOUND)));
    }

    @Test
    void editGetFormSuccess() throws Exception {
        Long id = 1L;
        Clazz clazz = new Clazz();
        Issue issue = new Issue();
        issue.setClazz(clazz);

        when(issueService.getIssueById(id)).thenReturn(issue);

        mockMvc.perform(get("/class/2/issues/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("component/issue :: formEdit"))
                .andExpect(model().attribute("issue", issue));
    }

    @Test
    void editIssueNotFund() throws Exception {
        Long id = 1L;
        when(issueService.getIssueById(id)).thenReturn(null);

        mockMvc.perform(get("/class/2/issues/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateSuccess() throws Exception {
        Long classId = 1L;
        Long issueId = 2L;
        String content = "New issue Content";
        Issue issue = new Issue();

        when(issueService.getIssueById(issueId)).thenReturn(issue);

        mockMvc.perform(put("/class/" + classId + "/issues/" + issueId).param("content", content).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(content)));
    }

    @Test
    void updateIssueNotFound() throws Exception {
        Long classId = 1L;
        Long issueId = 2L;
        String content = "New issue Content";

        when(issueService.getIssueById(issueId)).thenReturn(null);

        mockMvc.perform(put("/class/" + classId + "/issues/" + issueId).param("content", content).with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString(Messages.ISSUE_NOT_FOUND)));
    }
}