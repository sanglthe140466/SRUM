package com.srum.controller;

import com.srum.dto.ClazzListDTO;
import com.srum.dto.FeedbackDTO;
import com.srum.entity.Clazz;
import com.srum.entity.Feedback;
import com.srum.entity.Trainee;
import com.srum.entity.User;
import com.srum.service.ClassService;
import com.srum.service.FeedbackService;
import com.srum.service.TraineeService;
import com.srum.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 15/09/2021
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ClassService classService;

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @GetMapping
    public String showViewFeedback() {
        return "feedback";
    }

    @PostMapping("/add")
    public ResponseEntity<Object> saveFeedback(@RequestParam("name") String name,
                                               @RequestParam("url") String url,
                                               @RequestParam("content") String content,
                                               @RequestParam("class") Long classId) {
        Clazz clazz = classService.findClassById(classId);
        Feedback feedback = new Feedback(name, new Date(), content, url, clazz);
        feedbackService.saveFeedback(feedback);

        return ResponseEntity.ok(modelMapperUtil.map(feedback, FeedbackDTO.class));
    }

    @GetMapping("/list")
    public ResponseEntity<List<FeedbackDTO>> getListFeedback() {

        return ResponseEntity.ok(modelMapperUtil.getFeedbackListDTO(feedbackService.getAllFeedbackOrderByDateDesc()));
    }

    @GetMapping("/trainee")
    public String showListFeedbackForTrainee(Model model, Authentication authentication) {
        Long traineeId = null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof Trainee){
            traineeId =((User) principal).getId();
        }

        Trainee trainee = traineeService.getTraineeByTraineeId(traineeId);
        List<Feedback> feedbacks = feedbackService.getAllFeedbackByClassId(trainee.getClazz().getId());

        model.addAttribute("feedbacks", feedbacks);
        return "trainee-feedback";
    }

    @GetMapping("/class")
    public ResponseEntity<List<ClazzListDTO>> getListClass() {
        return ResponseEntity.ok(modelMapperUtil.getClazzListDTO(classService.getAllClassOrderByNameDesc()));
    }
}
