package com.srum.util.converter;

import com.srum.entity.Trainee;
import org.modelmapper.AbstractConverter;

import java.util.Set;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 05/09/2021
 */
public class TraineeSetToTraineeCount extends AbstractConverter<Set<Trainee>, Integer> {
    @Override
    protected Integer convert(Set<Trainee> trainees) {
        if(trainees != null) {
            return trainees.size();
        } else {
            return 0;
        }
    }
}
