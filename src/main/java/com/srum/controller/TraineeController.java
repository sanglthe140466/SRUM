package com.srum.controller;


import com.srum.dto.MistakeDTO;
import com.srum.dto.TraineeDTO;
import com.srum.entity.*;
import com.srum.service.*;
import com.srum.util.Messages;
import com.srum.util.ModelMapperUtil;
import com.srum.util.comparator.MistakeNameComparator;
import com.srum.util.type.TypeAttendance;
import com.srum.util.type.TypeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 07/09/2021
 */
@Controller
@RequestMapping("/trainee")
public class TraineeController {

    @Autowired
    private TraineeService traineeService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ClassService classService;

    @Autowired
    private MistakeService mistakeService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @Autowired
    private CommentService commentService;


    /**
     * This method show all trainee and return view + data
     *
     * @param model
     * @return "trainee-list" view
     * @Author : sanglthe140466
     */
    @GetMapping
    public String showTraineeView(Model model) {
        model.addAttribute("traineeList", traineeService.getAllTrainees());
        return "trainee-list";
    }

    /**
     * This method show attendance list by trainee and return view + data
     *
     * @param id
     * @return "attendance-list" view
     * @Author : sanglthe140466
     */
    @GetMapping("{traineeId}/attendance")
    public String showAttendance(@PathVariable("traineeId") Long id, Model model) {
        model.addAttribute("attendanceList", attendanceService.getAttendanceListByTrainee(id));
        return "component/attendance-list";
    }

    @GetMapping("/{id}")
    public String showViewTraineeDetail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("trainee", traineeService.getTraineeByTraineeId(id));
        model.addAttribute("avgScore", traineeService.getAvgScoreByTraineeId(id));
        model.addAttribute("totalAttendance", traineeService.countTotalAttendanceByTraineeId(id));
        model.addAttribute("typePresent", traineeService.countAttendanceTypePresentByTraineeId(TypeAttendance.P, id));
        return "view-trainee-detail";
    }

    @GetMapping("/{traineeId}/comment")
    public String getComment(@PathVariable("traineeId") Long id, Model model) {
        model.addAttribute("traineeId", id);
        model.addAttribute("comments", commentService.getAllCommentByTraineeId(id));
        return "component/view-comment";
    }

    @GetMapping("{traineeId}/interview-result")
    public String getInterviewResultByTrainee(@PathVariable Long traineeId, Model model) {
        model.addAttribute("interviewResult", interviewService.getAllInterviewResultByTraineeId(traineeId));
        return "component/interview-result";
    }

    @GetMapping("/{traineeId}/score")
    public String showScoreList(@PathVariable("traineeId") Long id, Model model) {
        model.addAttribute("scores", scoreService.getAllScoreByTraineeId(id));
        return "component/score-list";

    }

    @GetMapping("/{traineeId}/mistake")
    public String getAllMistake(@PathVariable("traineeId") Long id, Model model) {
        model.addAttribute("trainee", traineeService.getTraineeByTraineeId(id));
        model.addAttribute("mistakeNameComparator", new MistakeNameComparator());
        return "component/view-mistake";
    }

    /**
     * Save new mistake into database
     *
     * @param mistakeDTO
     * @param bindingResult
     * @param traineeId
     * @return
     * @author ThoNN1
     */
    @PostMapping("/{traineeId}/mistake")
    public ResponseEntity<Object> storeMistake(@Valid @ModelAttribute MistakeDTO mistakeDTO,
                                               BindingResult bindingResult,
                                               @PathVariable("traineeId") Long traineeId) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Trainee trainee = traineeService.getTraineeByTraineeId(traineeId);
        if (trainee == null) {
            return new ResponseEntity<>(Messages.TRAINEE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Mistake mistake = modelMapperUtil.map(mistakeDTO, Mistake.class);
        mistake.setTrainee(trainee);
        mistakeService.save(mistake);

        return ResponseEntity.ok(modelMapperUtil.map(mistake, MistakeDTO.class));
    }

    /**
     * Get add mistake form
     *
     * @param traineeId
     * @param model
     * @return
     * @author ThoNN1
     */
    @GetMapping("/{traineeId}/mistake/create")
    public String getMistakeForm(@PathVariable("traineeId") Long traineeId, Model model) {
        model.addAttribute("traineeId", traineeId);
        return "component/mistake :: addMistake";
    }


    @GetMapping("/{traineeId}/mistake/update/{mistakeId}")
    public String showUpdateMistakeForm(@PathVariable Long mistakeId, Model model) {
        model.addAttribute("Mistake", mistakeService.findMistakeById(mistakeId));
        return "component/mistake :: addMistake";
    }

    @PutMapping("/{traineeId}/mistake/update")
    public ResponseEntity<Object> updateMistake(@Valid @ModelAttribute MistakeDTO mistakeDTO,
                                                BindingResult bindingResult,
                                                @PathVariable("traineeId") Long traineeId) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Trainee trainee = traineeService.getTraineeByTraineeId(traineeId);
        if (trainee == null) {
            return new ResponseEntity<>(Messages.TRAINEE_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        Mistake mistake = modelMapperUtil.map(mistakeDTO, Mistake.class);
        mistake.setTrainee(trainee);
        mistakeService.save(mistake);

        return ResponseEntity.ok(modelMapperUtil.map(mistake, MistakeDTO.class));
    }

    /**
     * delete Mistake
     *
     * @param mistakeId
     * @param model
     * @author CuongLH6
     * @rerturn
     */
    @DeleteMapping("/{traineeId}/mistake/{mistakeId}/delete")
    public ResponseEntity<Object> deleteMistake(@PathVariable("mistakeId") Long mistakeId, Model model) {
        mistakeService.deleteMistakebyId(mistakeId);
        return ResponseEntity.ok("success");
    }

    /*
     * Save trainee to database
     *
     * @param traineeDTO
     * @param bindingResult
     * @return
     * @author sanglthe140466 (Trung Sang)
     */
    @PostMapping("/save")
    public String createTrainee(@Valid @ModelAttribute("trainee") TraineeDTO traineeDTO, BindingResult bindingResult,
                                RedirectAttributes redirectAttributes, Model model) {
        Trainee trainee;
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldError();
        } else {
            if (traineeService.getTraineeByAccountAndEmail(traineeDTO.getAccount(), traineeDTO.getEmail()) != null && traineeDTO.getId() == null) {
                model.addAttribute("error", Messages.DUPLICATED_FIELD);
            } else {
                if (traineeDTO.getType().equals(TypeClass.FRESHER)) {
                    trainee = modelMapperUtil.map(traineeDTO, Fresher.class);
                } else {
                    trainee = modelMapperUtil.map(traineeDTO, Internship.class);
                }
                if (traineeService.saveTrainee(trainee)) {
                    redirectAttributes.addFlashAttribute("success", Messages.ADD_TRAINEE_SUCCESS);
                    return "redirect:/trainee/" + trainee.getId();
                } else {
                    model.addAttribute("error", Messages.ERROR);
                }
            }
        }
        model.addAttribute("classList", classService.getAllClassOrderByNameDesc());
        return "form-trainee-manage";
    }


    /**
     * Show trainee form
     *
     * @param model
     * @return Author : sanglthe140466 (Trung Sang)
     */
    @GetMapping("/form/{classID}")
    public String showTraineeForm(@PathVariable("classID") Long classID, Model model) {
        model.addAttribute("trainee", new TraineeDTO());
        model.addAttribute("classFind", classID);
        model.addAttribute("classList", classService.getAllClassOrderByNameDesc());
        return "form-trainee-manage";
    }

    /**
     * Show data of trainee by id and return view
     *
     * @param model
     * @param traineeID
     * @return Author : sanglthe140466 (Trung Sang)
     */
    @GetMapping("/update/{traineeID}")
    public String showUpdateTraineeView(@PathVariable("traineeID") Long traineeID, Model model) {
        Trainee trainee = traineeService.getTraineeByTraineeId(traineeID);
        TraineeDTO traineeDTO = modelMapperUtil.map(trainee, TraineeDTO.class);
        if (trainee instanceof Fresher) {
            traineeDTO.setType(TypeClass.FRESHER);
        } else if (trainee instanceof Internship) {
            traineeDTO.setType(TypeClass.INTERN);
        }
        model.addAttribute("trainee", traineeDTO);
        model.addAttribute("classFind", trainee.getClazz().getId());
        model.addAttribute("classList", classService.getAllClassOrderByNameDesc());
        return "form-trainee-manage";
    }

    @PostMapping("/save/{traineeID}")
    public String updateTrainee(@Valid @ModelAttribute("trainee") TraineeDTO traineeDTO,
                                BindingResult bindingResult,
                                Model model) {
        Trainee trainee;
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldError();
        } else {
            trainee = (Trainee) modelMapperUtil.mapObject(traineeDTO,
                    traineeService.getTraineeByTraineeId(traineeDTO.getId()));
            if (traineeService.saveTrainee(trainee)) {
                return "redirect:/trainee/" + trainee.getId();
            } else {
                model.addAttribute("error", Messages.ERROR);
            }
        }
        model.addAttribute("classList", classService.getAllClassOrderByNameDesc());
        return "form-trainee-manage";
    }

    @PostMapping("/{traineeId}/comment/create")
    public String saveComment(@ModelAttribute Comment comment, @PathVariable("traineeId") Long id, Model model, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        comment.setUser((User) principal);
        commentService.addComment(comment);
        model.addAttribute("traineeId", id);
        model.addAttribute("comments", commentService.getAllCommentByTraineeId(id));
        return "component/view-comment";

    }

}

