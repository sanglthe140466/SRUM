package com.srum.controller;

import com.srum.dto.IssueDTO;
import com.srum.entity.Clazz;
import com.srum.entity.Issue;
import com.srum.service.ClassService;
import com.srum.service.IssueService;
import com.srum.util.Messages;
import com.srum.util.ModelMapperUtil;
import com.srum.util.comparator.IssueDateDescComparator;
import com.srum.util.comparator.SolutionDateDescComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Controller
@RequestMapping("/class/{classId}/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private ClassService classService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @GetMapping
    public String getAllIssues(@PathVariable("classId") Long classId, Model model) {
        Clazz clazz = classService.findClassById(classId);
        model.addAttribute("clazz", clazz);
        model.addAttribute("issueDateComparator", new IssueDateDescComparator());
        model.addAttribute("solutionDateComparator", new SolutionDateDescComparator());
        return "list-issues";
    }

    @PostMapping
    public ResponseEntity<Object> saveIssue(@PathVariable("classId") Long classId,
                                            @RequestParam("content") String content) {
        Clazz clazz = classService.findClassById(classId);
        if (clazz == null) {
            return new ResponseEntity<>(Messages.CLASS_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        Issue issue = new Issue(content, new Date(), clazz);
        issueService.saveIssue(issue);

        return ResponseEntity.ok(modelMapperUtil.map(issue, IssueDTO.class));
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, @PathVariable("classId") Long classId, Model model,
                       HttpServletResponse response) {
        Issue issue = issueService.getIssueById(id);
        if (issue == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            issue = new Issue();
        }
        model.addAttribute("issue", issue);
        model.addAttribute("classId", classId);
        return "component/issue :: formEdit";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestParam("content") String content) {
        Issue issue = issueService.getIssueById(id);
        if (issue == null) {
            return new ResponseEntity<>(Messages.ISSUE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        issue.setContent(content);
        issueService.saveIssue(issue);

        return ResponseEntity.ok(modelMapperUtil.map(issue, IssueDTO.class));
    }
}
