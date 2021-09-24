package com.srum.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "date", columnDefinition = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private Issue issue;

    public Solution() {
    }

    public Solution(String content, Date date, Issue issue) {
        this.content = content;
        this.date = date;
        this.issue = issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
