package com.srum.controller;

import com.srum.dto.ClazzDTO;
import com.srum.dto.ClazzListDTO;
import com.srum.entity.Clazz;
import com.srum.service.ClassService;
import com.srum.service.TrainerService;
import com.srum.util.Messages;
import com.srum.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 31/08/2021
 */
@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @GetMapping
    public String showViewClassList(Model model) {
        List<Clazz> clazzes = classService.getAllClassOrderByNameDesc();
        List<ClazzListDTO> clazzViewDTOS = modelMapperUtil.getClazzListDTO(clazzes);
        model.addAttribute("classes", clazzViewDTOS);
        return "view-class-list";
    }

    @GetMapping("/add")
    public String showViewAddClass(Model model) {
        model.addAttribute("clazz", new Clazz());
        model.addAttribute("trainer", trainerService.getAllTrainer());
        return "form-class-planning";
    }

    @GetMapping("/update/{classId}")
    public String showViewUpdateClass(@PathVariable Long classId, Model model) {
        model.addAttribute("clazz", classService.findClassById(classId));
        model.addAttribute("trainer", trainerService.getAllTrainer());
        return "form-class-planning";
    }

    @PostMapping("/save")
    public String addPlaning(@Valid @ModelAttribute("clazz") ClazzDTO clazzDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        String message = "";
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldError();
        } else {
            if ((classService.getClassByName(clazzDTO.getName()) != null) && (clazzDTO.getId() == null)) {
                message = Messages.DUPLICATED_CLASS_NAME;

            } else if (classService.getTrainerById(clazzDTO.getTrainer().getId()) == null) {
                message = Messages.TRAINER_NOT_FOUND;

            } else {
                Clazz clazz = null;
                if (clazzDTO.getId() == null) {
                    clazz = (Clazz) modelMapperUtil.mapObject(clazzDTO, new Clazz());
                    message = Messages.ADD_CLASS_SUCCESS;
                }else {
                    clazz = (Clazz) modelMapperUtil.mapObject(clazzDTO, classService.findClassById(clazzDTO.getId()));
                    message = Messages.UPDATE_CLASS_SUCCESS;
                }

                if (classService.saveClass(clazz)) {
                    redirectAttributes.addFlashAttribute("success", message);
                    return "redirect:/class";
                } else {
                    message = Messages.ERROR;
                }
            }
        }
        model.addAttribute("error", message);
        model.addAttribute("trainer", trainerService.getAllTrainer());
        return "form-class-planning";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/{id}")
    public String showClassDetail(@PathVariable Long id, Model model) {
        model.addAttribute("class", classService.findClassById(id));
        return "view-class-detail";
    }
}
