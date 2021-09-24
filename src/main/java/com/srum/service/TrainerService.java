package com.srum.service;

import com.srum.entity.Trainer;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 01/09/2021
 */
public interface TrainerService {
    List<Trainer> getAllTrainer();
    Trainer getTrainerByClassId(Long classId);
}
