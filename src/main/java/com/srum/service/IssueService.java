package com.srum.service;

import com.srum.entity.Issue;

import java.util.List;

public interface IssueService {
    List<Issue> getAllIssues();

    List<Issue> findAllIssuesByClazzId(Long clazzId);

    void saveIssue(Issue issue);

    Issue getIssueById(Long issueId);
}
