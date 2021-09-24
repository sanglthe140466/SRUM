package com.srum.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 05/09/2021
 */
public class ClazzListDTO implements Serializable {
    private Long id;

    private String name;

    private Date openDate;

    private Date endDate;

    private String note;

    private String type;

    private Integer planCount;

    private Integer status;

    private String trainerName;

    private Integer traineeCount;

    public ClazzListDTO() {
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

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public Integer getTraineeCount() {
        return traineeCount;
    }

    public void setTraineeCount(Integer traineeCount) {
        this.traineeCount = traineeCount;
    }
}
