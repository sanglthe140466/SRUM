package com.srum.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Trainee")
public class Trainee extends User implements Serializable {
    @Column(name = "branch")
    private String branch;

    @Column(name = "parent_department")
    private String parentDepartment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "rec_interview_date", columnDefinition = "date")
    private Date recInterviewDate;

    @Column(name = "rec_interview_status")
    private String recInterviewStatus;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private String status;

    @Column(name = "university")
    private String university;

    @Column(name = "faculty")
    private String faculty;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private Clazz clazz;

    @OneToMany(mappedBy = "trainee")
    private Set<Score> scores = new HashSet<>();

    @OneToMany(mappedBy = "trainee")
    private Set<Mistake> mistakes = new HashSet<>();

    @OneToMany(mappedBy = "trainee")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "trainee")
    private Set<Interview> interviews = new HashSet<>();

    public Trainee() {
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Date getRecInterviewDate() {
        return recInterviewDate;
    }

    public void setRecInterviewDate(Date recInterviewDate) {
        this.recInterviewDate = recInterviewDate;
    }

    public String getRecInterviewStatus() {
        return recInterviewStatus;
    }

    public void setRecInterviewStatus(String recInterviewStatus) {
        this.recInterviewStatus = recInterviewStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Set<Mistake> getMistakes() {
        return mistakes;
    }

    public void setMistakes(Set<Mistake> mistakes) {
        this.mistakes = mistakes;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(Set<Interview> interviews) {
        this.interviews = interviews;
    }
}
