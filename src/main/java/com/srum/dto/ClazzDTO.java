package com.srum.dto;

import com.srum.entity.Issue;
import com.srum.entity.Trainee;
import com.srum.entity.Trainer;
import com.srum.util.annotation.ValidDateRange;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 02/09/2021
 */
@ValidDateRange
public class ClazzDTO implements Serializable {
    private Long id;

    @NotBlank(message = "Please input class name")
    @Size(max = 100, message = "Class name no more than 100 characters")
    private String name;

    @NotNull(message = "Please input open date")
    @Future(message = "Open date that must not be greater than today's date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date openDate;

    @NotNull(message = "Please input end date")
    @Future(message = "End date that must not be greater than today's date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String note;

    @NotNull(message = "Please input type")
    private String type;

    @NotNull(message = "Please input plan count")
    private Integer planCount;

    @NotNull(message = "Please input status")
    private Integer status;

    @NotNull(message = "Please input trainer")
    private Trainer trainer;

    private Set<Trainee> trainees = new HashSet<>();

    private Set<Issue> issues = new HashSet<>();

    public ClazzDTO() {
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
}
