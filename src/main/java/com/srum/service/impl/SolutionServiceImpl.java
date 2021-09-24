package com.srum.service.impl;

import com.srum.entity.Solution;
import com.srum.repository.SolutionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 05/09/2021
 */
@Service
public class SolutionServiceImpl implements com.srum.service.SolutionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SolutionServiceImpl.class);

    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    @Transactional
    public boolean saveSolution(Solution solution) {
        boolean check = false;
        try {
            solutionRepository.save(solution);
            check = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return check;
    }

    @Override
    public Solution getSolutionById(Long id) {
        return solutionRepository.findById(id).get();
    }
}
