package com.srum.service;

import com.srum.entity.Trainee;
import com.srum.util.type.TypeAttendance;

import java.util.List;

public interface TraineeService {
    List<Trainee> getAllTraineeByClassId(Long classId);

    List<Trainee> getAllTrainees();

    Trainee getTraineeByTraineeId(Long traineeId);

    Trainee getTraineeByAccountAndEmail(String account,String email);

    Float getAvgScoreByTraineeId(Long traineId);

    boolean saveTrainee(Trainee trainee);

    int countTotalAttendanceByTraineeId(Long traineeId);

    int countAttendanceTypePresentByTraineeId(TypeAttendance type, Long traineeId);
}
