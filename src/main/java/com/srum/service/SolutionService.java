package com.srum.service;

import com.srum.entity.Solution;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 05/09/2021
 */
public interface SolutionService {
    boolean saveSolution(Solution solution);

    Solution getSolutionById(Long id);
}
