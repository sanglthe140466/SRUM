package com.srum.service.impl;
import com.srum.entity.Issue;
import com.srum.repository.IssueRepository;
import com.srum.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class IssueServiceimpl implements IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Override
    public List<Issue> getAllIssues() {
       return issueRepository.findAll();
    }

    @Override
    @Transactional
    public List<Issue> findAllIssuesByClazzId(Long clazzId) {
        return issueRepository.findAllIssuesByClazzId(clazzId);
    }

    @Override
    @Transactional
    public void saveIssue(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    @Transactional
    public Issue getIssueById(Long issueId) {
        return issueRepository.findById(issueId).get();
    }
}
