package com.srum.controller;

import com.srum.dto.SubjectDTO;
import com.srum.entity.Score;
import com.srum.entity.Subject;
import com.srum.entity.Trainee;
import com.srum.service.SubjectService;
import com.srum.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author Trung Sang (sanglthe140466)
 */
@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    /**
     * This function show view of subject
     *
     * @param model
     * @return view + data of subjects
     */
    @GetMapping("/subjects-list")
    public String showSubjectView(Model model) {
        List<Subject> list = subjectService.getSubjectList();
        List<SubjectDTO> subjectDTOList = modelMapperUtil.getSubjectListDTO(list);
        model.addAttribute("subjectDTOList", subjectDTOList);
        return "subjects-list";
    }

    /**
     * This function get subject-detaild and get trainee and return view
     *
     * @param id
     * @param model
     * @return view + data of subject detaild
     */
    @GetMapping("/subject-detaild/{id}")
    public String showSubjectDetaild(@PathVariable("id") Long id, Model model) {
        Subject subject = subjectService.getSubjectByID(id);
        Set<Trainee> trainees = new HashSet<>();
        Set<Score> scoreSet = subject.getScores();
        for (Score score : scoreSet) {
            trainees.add(score.getTrainee());
        }
        model.addAttribute("subject", subject);
        model.addAttribute("traineeList", trainees);
        return  "subject-detail";
    }
}

