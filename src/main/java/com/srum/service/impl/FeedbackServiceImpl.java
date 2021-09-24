package com.srum.service.impl;

import com.srum.entity.Feedback;
import com.srum.repository.FeedbackRepository;
import com.srum.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 15/09/2021
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public boolean saveFeedback(Feedback feedback) {
        boolean check = false;
        try {
            feedbackRepository.save(feedback);
            check = true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return check;
    }

    @Override
    public List<Feedback> getAllFeedbackOrderByDateDesc() {
        return feedbackRepository.findByOrderByConsultDateDesc();
    }

    @Override
    public List<Feedback> getAllFeedbackByClassId(Long classId) {
        return feedbackRepository.findAllByClazz_Id(classId);
    }
}
