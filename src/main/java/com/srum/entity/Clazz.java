package com.srum.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Class")
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "open_date", columnDefinition = "date", nullable = false)
    private Date openDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date", columnDefinition = "date", nullable = false)
    private Date endDate;

    @Column(name = "note")
    private String note;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "plan_count", nullable = false)
    private Integer planCount;

    @Column(name = "status", nullable = false)
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;

    @OneToMany(mappedBy = "clazz")
    private Set<Trainee> trainees = new HashSet<>();

    @OneToMany(mappedBy = "clazz")
    private Set<Issue> issues = new HashSet<>();

    @OneToMany(mappedBy = "clazz")
    private Set<Feedback> feedbacks = new HashSet<>();

    public Clazz() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPlanCount() {
        return planCount;
    }

    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Set<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Set<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
}
