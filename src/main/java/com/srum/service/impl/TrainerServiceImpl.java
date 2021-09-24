package com.srum.service.impl;

import com.srum.entity.Clazz;
import com.srum.entity.Trainer;
import com.srum.repository.ClassRepository;
import com.srum.repository.TrainerRepository;
import com.srum.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 01/09/2021
 */
@Service
public class TrainerServiceImpl implements TrainerService {
    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private ClassRepository classRepository;

    @Override
    @Transactional
    public List<Trainer> getAllTrainer(){
        return trainerRepository.findAll();
    }

    @Override
    @Transactional
    public Trainer getTrainerByClassId(Long classId) {
        return classRepository.findClazzById(classId).getTrainer();
    }
}
