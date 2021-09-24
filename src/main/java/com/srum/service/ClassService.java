package com.srum.service;

import com.srum.entity.Clazz;
import com.srum.entity.Trainer;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 31/08/2021
 */
public interface ClassService {
    List<Clazz> getAllClassOrderByNameDesc();

    Clazz getClassByName(String name);

    boolean saveClass(Clazz clazz);

    Trainer getTrainerById(Long id);

    Clazz findClassById(Long classId);

}
