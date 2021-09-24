package com.srum.dto;

import com.srum.entity.Clazz;
import com.srum.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public class TraineeDTO extends UserDTO implements Serializable {

    @NotBlank
    @Size(min = 3, message = "At least 3 characters")
    private String branch;

    @NotBlank
    @Size(min = 3, message = "At least 3 characters")
    private String parentDepartment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "This feild is required")
    private Date recInterviewDate;

    private String recInterviewStatus;

    private String note;

    private String status;

    private String type;

    @NotBlank
    @Size(min = 3, message = "At least 3 characters")
    private String university;

    @NotBlank
    @Size(min = 3, message = "At least 3 characters")
    private String faculty;

    private Clazz clazz;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date universityGraduationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fullTimeWorkingAvailable;

    public Date getUniversityGraduationDate() {
        return universityGraduationDate;
    }

    public void setUniversityGraduationDate(Date universityGraduationDate) {
        this.universityGraduationDate = universityGraduationDate;
    }

    public Date getFullTimeWorkingAvailable() {
        return fullTimeWorkingAvailable;
    }

    public void setFullTimeWorkingAvailable(Date fullTimeWorkingAvailable) {
        this.fullTimeWorkingAvailable = fullTimeWorkingAvailable;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
