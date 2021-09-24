package com.srum.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Fresher")
public class Fresher extends Trainee {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "university_graduation_date",columnDefinition = "date")
    private Date universityGraduationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "full_time_working_available",columnDefinition = "date")
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
}
