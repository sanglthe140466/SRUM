package com.srum.service;

import com.srum.entity.Feedback;

import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 15/09/2021
 */
public interface FeedbackService {
    boolean saveFeedback(Feedback feedback);

    List<Feedback> getAllFeedbackOrderByDateDesc();

    List<Feedback> getAllFeedbackByClassId(Long classId);
}
