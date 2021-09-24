package com.srum.service;

import com.srum.entity.Score;

import java.util.List;

/**
 * AUTHOR:VuGT
 */
public interface ScoreService {
    List<Score> getAllScoreByTraineeId(Long traineeId);
}
