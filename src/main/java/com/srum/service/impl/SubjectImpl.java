package com.srum.service.impl;

import com.srum.entity.Subject;
import com.srum.repository.SubjectRepository;
import com.srum.service.SubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author Trung Sang (sanglthe140466)
 */
@Service
public class SubjectImpl implements SubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectImpl.class);

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional
    public List<Subject> getSubjectList() {
        return subjectRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Override
    @Transactional
    public Subject getSubjectByID(Long id) {
        Subject subject = null;
        try {
            subject = subjectRepository.getById(id);
        } catch (EntityNotFoundException e) {
            LOGGER.error(String.valueOf(e.getStackTrace()));
        }
        return subject;
    }


}
