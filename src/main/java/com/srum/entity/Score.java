package com.srum.entity;

import com.srum.entity.id.ScoreId;

import javax.persistence.*;


/**
 * @author ThoNN1
 */
@Entity
@Table(name = "Score")
@IdClass(ScoreId.class)
public class Score {
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private Float value;

    @Column(name = "note")
    private String note;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Id
    @ManyToOne
    @JoinColumn(name = "trainee_id", nullable = false)
    private Trainee trainee;

    public Score() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }
}
