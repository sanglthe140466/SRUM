package com.srum.service.impl;

import com.srum.entity.Interview;
import com.srum.repository.InterviewRepository;
import com.srum.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 09/09/2021
 */
@Service
public class InterviewServiceImpl implements InterviewService {
    @Autowired
    private InterviewRepository interviewRepository;

    @Transactional
    @Override
    public List<Interview> getAllInterviewResultByTraineeId(Long traineeId){
        return interviewRepository.findAllByTraineeId(traineeId);
    }
}
