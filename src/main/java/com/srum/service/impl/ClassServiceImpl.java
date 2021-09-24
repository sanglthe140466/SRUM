package com.srum.service.impl;

import com.srum.entity.Clazz;
import com.srum.entity.Trainer;
import com.srum.repository.ClassRepository;
import com.srum.repository.TrainerRepository;
import com.srum.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 31/08/2021
 */
@Service
public class ClassServiceImpl implements ClassService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    @Transactional
    public List<Clazz> getAllClassOrderByNameDesc() {
        return classRepository.findByOrderByNameDesc();
    }

    @Override
    @Transactional
    public boolean saveClass(Clazz clazz) {
        boolean check = false;
        try {
            classRepository.save(clazz);
            check = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return check;
    }

    @Override
    @Transactional
    public Clazz getClassByName(String name) {
        return classRepository.findClazzByName(name);
    }

    @Override
    @Transactional
    public Trainer getTrainerById(Long id) {
        return trainerRepository.findTrainerById(id);
    }

    @Override
    @Transactional
    public Clazz findClassById(Long classId) {
        return classRepository.findClazzById(classId);
    }
}
