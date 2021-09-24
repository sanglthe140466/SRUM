package com.srum.service.impl;

import com.srum.entity.Score;
import com.srum.repository.ScoreRepository;
import com.srum.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * AUTHOR:VuGT
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    @Transactional
    public List<Score> getAllScoreByTraineeId(Long traineeId) {
        return scoreRepository.getAllByTraineeId(traineeId);
    }
}
