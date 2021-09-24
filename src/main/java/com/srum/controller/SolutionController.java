package com.srum.controller;

import com.srum.dto.SolutionDTO;
import com.srum.entity.Issue;
import com.srum.entity.Solution;
import com.srum.service.IssueService;
import com.srum.service.SolutionService;
import com.srum.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 05/09/2021
 */
@Controller
@RequestMapping("/solutions")
public class SolutionController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @GetMapping("/issues/{issueId}")
    public String getFormAddSolution(@PathVariable("issueId") Long issuesId, Model model) {
        model.addAttribute("issueId", issuesId);
        return "component/issue :: formCreateSolution";
    }

    @PostMapping("/issues/{issueId}")
    public ResponseEntity<Object> store(@PathVariable("issueId") Long issuesId,
                                        @RequestParam("content") String content) {
        Issue issue = issueService.getIssueById(issuesId);
        if (issue == null) {
            return ResponseEntity.notFound().build();
        }
        Solution solution = new Solution(content, new Date(), issue);
        solutionService.saveSolution(solution);

        return ResponseEntity.ok(modelMapperUtil.map(solution, SolutionDTO.class));
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model, HttpServletResponse response) {
        Solution solution = solutionService.getSolutionById(id);
        if (solution == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            solution = new Solution();
        }
        model.addAttribute("solution", solution);

        return "component/issue :: formEditSolution";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestParam("content") String content) {
        Solution solution = solutionService.getSolutionById(id);
        if (solution == null) {
            return ResponseEntity.notFound().build();
        }
        solution.setContent(content);
        solutionService.saveSolution(solution);

        return ResponseEntity.ok(modelMapperUtil.map(solution, SolutionDTO.class));
    }
}
