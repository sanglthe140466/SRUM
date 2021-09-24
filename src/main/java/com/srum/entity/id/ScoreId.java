package com.srum.entity.id;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author ThoNN1
 */
public class ScoreId implements Serializable {
    private Long subject;
    private Long trainee;

    public ScoreId() {
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public Long getTrainee() {
        return trainee;
    }

    public void setTrainee(Long trainee) {
        this.trainee = trainee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreId scoreId = (ScoreId) o;
        return Objects.equals(subject, scoreId.subject) && Objects.equals(trainee, scoreId.trainee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, trainee);
    }
}
