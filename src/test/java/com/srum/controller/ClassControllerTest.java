package com.srum.controller;

import com.srum.dto.ClazzDTO;
import com.srum.entity.Clazz;
import com.srum.entity.Trainer;
import com.srum.main.SRUMApplication;
import com.srum.service.ClassService;
import com.srum.util.Messages;
import com.srum.util.ModelMapperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 10/09/2021
 */
@SpringBootTest(classes = {SRUMApplication.class})
@AutoConfigureMockMvc(addFilters = false)
class ClassControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClassService classService;

    @MockBean
    private ModelMapperUtil modelMapperUtil;

    private Trainer trainer;

    private Clazz clazz;

    @BeforeEach
    void setUp() {
        trainer = new Trainer();
        clazz = (Clazz) modelMapperUtil.mapObject(new ClazzDTO(), new Clazz());
    }

    @Test
    void test_show_add_class_page() throws Exception {
        mockMvc.perform(get("/class/add"))
                .andExpect(view().name("form-class-planning"));
    }

    @Test
    void test_show_update_class_page() throws Exception {
        Clazz clazzInDb = new Clazz();
        clazzInDb.setId(1L);

        when(classService.findClassById(1L)).thenReturn(clazzInDb);
        mockMvc.perform(get("/class/update/{classId}", 1L))
                .andExpect(model().attribute("clazz", hasProperty("id", is(1L))))
                .andExpect(view().name("form-class-planning"));
    }

    @Test
    void test_show_class_list_page() throws Exception {
        mockMvc.perform(get("/class"))
                .andExpect(view().name("view-class-list"));
    }

    @Test
    void test_save_class_with_input_is_valid() throws Exception {
        trainer.setId(2L);
        when(classService.getTrainerById(2L)).thenReturn(trainer);

        when(classService.saveClass(clazz)).thenReturn(true);

        mockMvc.perform(post("/class/save")
                .contentType(MediaType.ALL)
                .param("name", "C01")
                .param("openDate", "2021-10-01T00:00:00.000Z")
                .param("endDate", "2021-12-31T00:00:00.000Z")
                .param("planCount", String.valueOf(30))
                .param("type", "Fresher")
                .param("status", String.valueOf(0))
                .param("trainer.id", String.valueOf(trainer.getId()))
        )
                .andExpect(redirectedUrl("/class"))
                .andExpect(flash().attribute("success", Messages.ADD_CLASS_SUCCESS));
    }

    @Test
    void test_save_class_with_duplicate_class_name() throws Exception {
        trainer.setId(2L);
        when(classService.getTrainerById(2L)).thenReturn(trainer);

        Clazz clazzInDb = new Clazz();
        clazzInDb.setName("C188");
        when(classService.getClassByName("C188")).thenReturn(clazzInDb);

        when(classService.saveClass(clazz)).thenReturn(true);

        mockMvc.perform(post("/class/save")
                .contentType(MediaType.ALL)
                .param("name", clazzInDb.getName())
                .param("openDate", "2021-10-01T00:00:00.000Z")
                .param("endDate", "2021-12-31T00:00:00.000Z")
                .param("planCount", String.valueOf(30))
                .param("type", "Fresher")
                .param("status", String.valueOf(0))
                .param("trainer.id", String.valueOf(trainer.getId()))
        )
                .andExpect(view().name("form-class-planning"))
                .andExpect(model().attribute("error", Messages.DUPLICATED_CLASS_NAME));
    }

    @Test
    void test_save_class_with_trainer_not_found() throws Exception {
        trainer.setId(10L);
        when(classService.getTrainerById(1L)).thenReturn(trainer);

        when(classService.saveClass(clazz)).thenReturn(true);

        mockMvc.perform(post("/class/save")
                .contentType(MediaType.ALL)
                .param("name", "C01")
                .param("openDate", "2021-10-01T00:00:00.000Z")
                .param("endDate", "2021-12-31T00:00:00.000Z")
                .param("planCount", String.valueOf(30))
                .param("type", "Fresher")
                .param("status", String.valueOf(0))
                .param("trainer.id", String.valueOf(trainer.getId()))
        )
                .andExpect(view().name("form-class-planning"))
                .andExpect(model().attribute("error", Messages.TRAINER_NOT_FOUND));
    }
    @Test
    void test_show_class_detail() throws Exception {
        mockMvc.perform(get("/class/{classId}", 1L))
                .andExpect(view().name("view-class-list"));
    }
}