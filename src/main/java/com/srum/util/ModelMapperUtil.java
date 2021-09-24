package com.srum.util;

import com.srum.dto.ClazzListDTO;
import com.srum.dto.FeedbackDTO;
import com.srum.dto.SubjectDTO;
import com.srum.entity.Clazz;
import com.srum.entity.Feedback;
import com.srum.entity.Subject;
import com.srum.util.converter.TraineeSetToTraineeCount;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 02/09/2021
 */
@Component
public class ModelMapperUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelMapperUtil.class);

    private final ModelMapper modelMapper = new ModelMapper();

    public Object mapObject(Object modelDTO, Object modelEntity) {
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(modelDTO, modelEntity.getClass());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public <D> D map(Object src, Class<D> des) {
        try {
            modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper.map(src, des);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<ClazzListDTO> getClazzListDTO(List<Clazz> clazzes) {
        modelMapper.typeMap(Clazz.class, ClazzListDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getTrainer().getName(),
                    ClazzListDTO::setTrainerName);
            mapper.using(new TraineeSetToTraineeCount()).map(Clazz::getTrainees,
                    ClazzListDTO::setTraineeCount);
        });
        return clazzes.stream()
                .map(clazz -> modelMapper.map(clazz, ClazzListDTO.class))
                .collect(Collectors.toList());
    }

    public List<SubjectDTO> getSubjectListDTO(List<Subject> subjectList) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Subject.class, SubjectDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getTrainer().getName(), SubjectDTO::setTrainerName);
        });
        return modelMapper.map(subjectList, new TypeToken<List<SubjectDTO>>() {}.getType());
    }

    public List<FeedbackDTO> getFeedbackListDTO(List<Feedback> feedbackList) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.typeMap(Feedback.class, FeedbackDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getClazz().getName(), FeedbackDTO::setClassName);
        });
        return modelMapper.map(feedbackList, new TypeToken<List<FeedbackDTO>>() {}.getType());
    }
}
