package com.srum.entity;

import javax.persistence.*;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 09/09/2021
 */
@Entity
@Table(name = "Interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "skill", nullable = false)
    private String skill;

    @Column(name = "current_level", nullable = false)
    private Integer currentLevel;

    @Column(name = "max_level", nullable = false)
    private Integer maxLevel;

    @ManyToOne
    @JoinColumn(name = "trainee_id", referencedColumnName = "id")
    private Trainee trainee;

    public Interview() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }
}
