package com.srum.service;

import com.srum.entity.Interview;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 09/09/2021
 */
public interface InterviewService {
    List<Interview> getAllInterviewResultByTraineeId(Long traineeId);
}
